package com.example.demogateway.filter;

import com.example.demogateway.service.DiscoveryCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

@Component
@Order(3)
public class ArcataFilter implements GlobalFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArcataFilter.class);

    @Autowired
    DiscoveryCache cache;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        LOGGER.info("Going through Arcata Filter");


        //get service key
        ServerHttpRequest request = exchange.getRequest();
        String uri = request.getURI().getRawPath();
        LOGGER.info("==uri=="+uri);
        String[] uriParts = uri.split("/",6);
        String serviceKey = uriParts[2];
        String backendPath;
        //Check current cache;
        if(cache.get(serviceKey)!=null){
            backendPath = cache.get(serviceKey);
            LOGGER.info("Cache not null, backendPath = "+ backendPath);
        } else {
            //Calling Arcata service here
            RestTemplate restTemplate = new RestTemplate();
            String ResourceUrl
                    = "http://localhost:8081/destination/";
            ResponseEntity<String> response
                    = restTemplate.getForEntity(ResourceUrl + serviceKey, String.class);
            backendPath = response.getBody();
            //set cache
            cache.set(serviceKey,backendPath);
            LOGGER.info(response.getBody());
        }

        try {
            exchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR, new URI(backendPath));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }


        //retrieve access policy

        //retrieve service endpoint

        //check audience

        //check scope



        return chain.filter(exchange);
    }
}
