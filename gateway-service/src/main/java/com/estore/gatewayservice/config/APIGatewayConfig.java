package com.estore.gatewayservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIGatewayConfig {
    @Autowired
    private AuthFilter filter;
    @Value("${account_service}")
    private String account_service;
    @Value("${bank_account_service}")
    private String bank_account_service;
    @Value("${credit_service}")
    private String credit_service;
    @Value("${order_service}")
    private String order_service;
    @Value("${payment_service}")
    private String payment_service;
    @Value("${paypal_service}")
    private String paypal_service;
    @Value("${product_service}")
    private String product_service;
    @Value("${shipping_service}")
    private String shipping_service;
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("account-service",r->r.path("/accounts/**")
                        .filters(f->f.filter(filter))
                        .uri(account_service))
                .route("bank-account-service",r->r.path("/bankaccount/**")
                        .filters(f->f.filter(filter))
                        .uri(bank_account_service))
                .route("credit-service",r->r.path("/credit/**")
                        .filters(f->f.filter(filter))
                        .uri(credit_service))
                .route("order_service",r->r.path("/orders/**")
                        .filters(f->f.filter(filter))
                        .uri(order_service))
                .route("payment-service",r->r.path("/payment/**")
                        .filters(f->f.filter(filter))
                        .uri(payment_service))
                .route("paypal-service",r->r.path("/paypal/**")
                        .filters(f->f.filter(filter))
                        .uri(paypal_service))
                .route("product-service",r->r.path("/product/**")
                        .filters(f->f.filter(filter))
                        .uri(product_service))
                .route("shipping-service",r->r.path("/shipping/**")
                        .filters(f->f.filter(filter))
                        .uri(shipping_service))
                .build();
    }
}
