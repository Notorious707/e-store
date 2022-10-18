package com.estore.creditservice.service;

import com.estore.creditservice.model.CreditDTO;
import com.estore.creditservice.model.TransactionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@Service
public class CreditServiceImpl implements CreditService{

    @Value("${rest.api-secret}")
    private String secret;
    @Override
    public TransactionResponse makePayment(CreditDTO creditDTO, String authHeader) {
        if( !authHeader.equals(secret)){
            return new TransactionResponse("Invalid authorization", "UNAUTHORIZED");
        }
        if(creditDTO.getAmount() == null || creditDTO.getOrderId() == null){
            log.info("Payment is not made by Bank Account service");
            return new TransactionResponse("Payment is unsuccessful by Bank Account service", "APPROVED");
        }else
            log.info("Payment is made by Bank Account service");
        return new TransactionResponse("Payment is made by Bank Account service", "REJECTED");

    }
}
