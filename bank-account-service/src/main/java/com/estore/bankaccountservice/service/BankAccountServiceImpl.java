package com.estore.bankaccountservice.service;

import com.estore.bankaccountservice.model.BankAccountDTO;
import com.estore.bankaccountservice.model.TransactionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Slf4j
@Service
public class BankAccountServiceImpl implements BankAccountService{
    @Value("${app.jwt.jwtSecret}")
    private String secret;
    @Override
    public TransactionResponse makePayment(BankAccountDTO paymentDTO, String authHeader) {
        if( !authHeader.equals(secret)){
            return new TransactionResponse("Invalid authorization", "UNAUTHORIZED");
        }
        if(paymentDTO.getAmount() == null || paymentDTO.getOrderId() == null){
            log.info("Payment is not made by Bank Account service");
            return new TransactionResponse("Payment is unsuccessful by Bank Account service", "APPROVED");
        }else
            log.info("Payment is made by Bank Account service");
        return new TransactionResponse("Payment is made by Bank Account service", "REJECTED");

    }

}
