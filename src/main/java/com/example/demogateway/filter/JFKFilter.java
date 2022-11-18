package com.example.demogateway.filter;

import com.example.demogateway.service.TokenCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Order(1)
public class JFKFilter implements GlobalFilter {

    @Autowired
    TokenCache tokenCache;
    private static final Logger LOGGER = LoggerFactory.getLogger(JFKFilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        LOGGER.info("Going through JFK Filter");
        //Check for Authorization header

        String authorization;
        String eat;
        String iat;

        if(exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){

            authorization = CollectionUtils.firstElement(exchange.getRequest()
                    .getHeaders().get(HttpHeaders.AUTHORIZATION));
            LOGGER.info("==authorization received== ", authorization);
            //check if IAT available in cache
            eat=authorization;
            if(tokenCache.get(eat)!=null){
                iat=tokenCache.get(eat);
            } else {
                //Calling cam service here
                RestTemplate restTemplate = new RestTemplate();
                String ResourceUrl
                        = "http://localhost:8082/iat/";
                ResponseEntity<String> response
                        = restTemplate.getForEntity(ResourceUrl, String.class);
                iat = response.getBody();
                //set cache
                tokenCache.set(eat,iat);
                LOGGER.info(response.getBody());
            }

            LOGGER.info("No Token passed in header");
            //Throw exception here

            //if EAT, contacting CAM to fetch IAT and saving it in Cache
        }


        //Go through refresh token flow

        return chain.filter(exchange);
    }
}
