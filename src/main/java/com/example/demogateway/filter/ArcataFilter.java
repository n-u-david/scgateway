package com.example.demogateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@Order(3)
public class ArcataFilter implements GlobalFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArcataFilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        LOGGER.info("Going through Arcata Filter");
        return chain.filter(exchange);
    }
}
