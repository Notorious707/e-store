package com.estore.productservice.service;

import com.estore.productservice.model.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenServiceImpl implements TokenService {


    @Autowired
    private RestTemplate authServiceApi;

    public ProductResponse validateToken(String authorizationHeader) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, authorizationHeader);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<ProductResponse> response = authServiceApi.exchange("/auth/validate", HttpMethod.GET, requestEntity, ProductResponse.class);
        return response.getBody();
    }

}

