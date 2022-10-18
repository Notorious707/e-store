package com.estore.gatewayservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIGatewayConfig {
    @Value("${accountservice}")
    private String account_service;
    @Value("${bankaccountservice}")
    private String bank_account_service;
    @Value("${creditservice}")
    private String credit_service;
    @Value("${orderservice}")
    private String order_service;
    @Value("${paymentservice}")
    private String payment_service;
    @Value("${paypalservice}")
    private String paypal_service;
    @Value("${productservice}")
    private String product_service;
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("account-service",r->r.path("/accounts/**")
                        .uri(account_service))
                .route("auth-service",r->r.path("/auth/**")
                        .uri(account_service))
                .route("bank-account-service",r->r.path("/bankaccount/**")
                        .uri(bank_account_service))
                .route("credit-service",r->r.path("/credit/**")
                        .uri(credit_service))
                .route("order_service",r->r.path("/orders/**")
                        .uri(order_service))
                .route("payment-service",r->r.path("/payment/**")
                        .uri(payment_service))
                .route("paypal-service",r->r.path("/paypal/**")
                        .uri(paypal_service))
                .route("product-service",r->r.path("/product/**")
                        .uri(product_service))
                .build();
    }
}
