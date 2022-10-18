package com.estore.paymentservice.service;



import com.estore.paymentservice.model.ValidateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${account_service}")
    private String authSvcUrl;

    public ValidateDTO validateToken(String authorizationHeader) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, authorizationHeader);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<ValidateDTO> response = restTemplate.exchange(authSvcUrl + "/auth/validate", HttpMethod.GET, requestEntity, ValidateDTO.class);
        return response.getBody();
    }

}

