package com.estore.productservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@SpringBootApplication
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}
	@Value("${accountservice}")
	private String ACCOUNT_SERVICE_BASE_URL;
	@Value("${bankaccountservice}")
	private String BANK_ACCOUNT_SERVICE_BASE_URL;
	@Value("${creditservice}")
	private String CREDIT_SERVICE_BASE_URL;
	@Value("${orderservice}")
	private String ORDER_SERVICE_BASE_URL;
	@Value("${paymentservice}")
	private String PAYMENT_SERVICE_BASE_URL;
	@Value("${paypalservice}")
	private String PAYPAL_SERVICE_BASE_URL;
	@Value("${productservice}")
	private String PRODUCT_SERVICE_BASE_URL;
	@Value("${shippingservice}")
	private String SHIPPING_SERVICE_BASE_URL;
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
