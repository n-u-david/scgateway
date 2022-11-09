package com.example.demogateway.filter;

import com.example.demogateway.service.DiscoveryCache;
import com.example.demogateway.service.TokenCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@Order(3)
public class ArcataFilter implements GlobalFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArcataFilter.class);

    @Autowired
    DiscoveryCache cache;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        LOGGER.info("Going through Arcata Filter");
        //Check current cache;
        if(cache!=null)
            LOGGER.info("Cache not null");

        //Calling Arcata service here
        RestTemplate restTemplate = new RestTemplate();
        String ResourceUrl
                = "http://localhost:8081/destination";
        ResponseEntity<String> response
                = restTemplate.getForEntity(ResourceUrl + "/service001", String.class);
        LOGGER.info(response.getBody());

        //get service key

        //retrieve access policy

        //retrieve service endpoint

        //check audience

        //check scope



        return chain.filter(exchange);
    }
}
