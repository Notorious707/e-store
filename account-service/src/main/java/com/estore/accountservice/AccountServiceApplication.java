package com.estore.accountservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@SpringBootApplication
public class AccountServiceApplication {
	@Value("${account_service}")
	private String ACCOUNT_SERVICE_BASE_URL;
	@Value("${bank_account_service}")
	private String BANK_ACCOUNT_SERVICE_BASE_URL;
	@Value("${credit_service}")
	private String CREDIT_SERVICE_BASE_URL;
	@Value("${order_service}")
	private String ORDER_SERVICE_BASE_URL;
	@Value("${payment_service}")
	private String PAYMENT_SERVICE_BASE_URL;
	@Value("${paypal_service}")
	private String PAYPAL_SERVICE_BASE_URL;
	@Value("${product_service}")
	private String PRODUCT_SERVICE_BASE_URL;
	@Value("${shipping_service}")
	private String SHIPPING_SERVICE_BASE_URL;
	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}
	@Bean
	RestTemplate accountServiceApi(){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(ACCOUNT_SERVICE_BASE_URL));
		return restTemplate;
	}
	@Bean
	RestTemplate bankAccountServiceApi(){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(BANK_ACCOUNT_SERVICE_BASE_URL));
		return restTemplate;
	}
	@Bean
	RestTemplate creditServiceApi(){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(CREDIT_SERVICE_BASE_URL));
		return restTemplate;
	}
	@Bean
	RestTemplate orderServiceApi(){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(ORDER_SERVICE_BASE_URL));
		return restTemplate;
	}
	@Bean
	RestTemplate paymentServiceApi(){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(PAYMENT_SERVICE_BASE_URL));
		return restTemplate;
	}
	@Bean
	RestTemplate paypalServiceApi(){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(PAYPAL_SERVICE_BASE_URL));
		return restTemplate;
	}
	@Bean
	RestTemplate productServiceApi(){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(PRODUCT_SERVICE_BASE_URL));
		return restTemplate;
	}
	@Bean
	RestTemplate shippingServiceApi(){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(SHIPPING_SERVICE_BASE_URL));
		return restTemplate;
	}
}
