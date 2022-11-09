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
@Order(2)
public class RequestSizeGatewayFilter implements GlobalFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestSizeGatewayFilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        LOGGER.info("Going through RequestSizeGateway Filter");
        //check request size


        return chain.filter(exchange);
    }
}
