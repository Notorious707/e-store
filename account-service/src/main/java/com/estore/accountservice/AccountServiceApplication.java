package com.estore.accountservice;

import com.estore.accountservice.model.Account;
import com.estore.accountservice.model.Address;
import com.estore.accountservice.model.roles.ERole;
import com.estore.accountservice.model.roles.Role;
import com.estore.accountservice.repository.AccountRepository;
import com.estore.accountservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.List;

@SpringBootApplication
public class AccountServiceApplication {
    @Autowired
    public PasswordEncoder passwordEncoder;
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

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner demoData(RoleRepository roleRepository, AccountRepository accountRepository) {
        return args -> {
            List<Role> roles = roleRepository.findAll();
            if (roles.stream().count() == 0) {
                System.out.println("CREATED DEFAULT ROLES");
                Role role1 = new Role();
                Role role2 = new Role();
                role1.setName(ERole.ROLE_USER);
                role2.setName(ERole.ROLE_ADMIN);
                roleRepository.save(role1);
                roleRepository.save(role2);
                List<Account> users = accountRepository.findAll();
                if (users.stream().count() == 0) {
                    System.out.println("CREATED DEFAULT ADMIN");
                    Account account = new Account();
                    Address shippingAddress = new Address(
                            "USA",
                            "Iowa",
                            "Fairfield",
                            52557,
                            "1000 N 4th Street"
                    );
					account.setShippingAddress(shippingAddress);
                    account.getRoles().add(role2);
                    account.setEmail("diana@gmail.com");
                    account.setPassword(passwordEncoder.encode("pass1234"));
                    accountRepository.save(account);
                }
            }
        };
    }

    @Bean
    RestTemplate accountServiceApi() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(ACCOUNT_SERVICE_BASE_URL));
        return restTemplate;
    }

    @Bean
    RestTemplate bankAccountServiceApi() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(BANK_ACCOUNT_SERVICE_BASE_URL));
        return restTemplate;
    }

    @Bean
    RestTemplate creditServiceApi() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(CREDIT_SERVICE_BASE_URL));
        return restTemplate;
    }

    @Bean
    RestTemplate orderServiceApi() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(ORDER_SERVICE_BASE_URL));
        return restTemplate;
    }

    @Bean
    RestTemplate paymentServiceApi() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(PAYMENT_SERVICE_BASE_URL));
        return restTemplate;
    }

    @Bean
    RestTemplate paypalServiceApi() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(PAYPAL_SERVICE_BASE_URL));
        return restTemplate;
    }

    @Bean
    RestTemplate productServiceApi() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(PRODUCT_SERVICE_BASE_URL));
        return restTemplate;
    }

    @Bean
    RestTemplate shippingServiceApi() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(SHIPPING_SERVICE_BASE_URL));
        return restTemplate;
    }

}
