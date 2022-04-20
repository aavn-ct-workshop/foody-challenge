package com.workshop.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Router {

    @Value(value = "${notifier.host}")
    private String notifierHost;

    @Value(value = "${merchant.host}")
    private String merchantHost;
    
    @Value(value = "${order.host}")
    private String orderHost;

    @Value(value = "${driver.host}")
    private String driverHost;

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route(p -> p
                .path("/notifiers/test")
                .uri(notifierHost)
            )
            .route(p -> p
                .path("/notifiers")
                .filters(rw -> rw.rewritePath("/notifiers", "/"))
                .uri(notifierHost)
            )
            .route(p -> p
                .path("/merchants/foods")
                .uri(merchantHost)
            )
            .route(p -> p
                .path("/orders")
                .uri(orderHost)
            )
            .route(p -> p
                .path("/drivers")
                .uri(driverHost)
            )
            .build();
    }
}
