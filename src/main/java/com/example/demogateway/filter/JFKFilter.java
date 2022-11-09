package com.example.demogateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Order(1)
public class JFKFilter implements GlobalFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JFKFilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        LOGGER.info("Going through JFK Filter");

        //check if IAT available in cache

        //if EAT, contacting CAM to fetch IAT and saving it in Cache

        //Go through refresh token flow

        return chain.filter(exchange);
    }
}
