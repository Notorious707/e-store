package com.estore.paypalservice.service;

import com.estore.paypalservice.model.TransactionResponse;
import com.estore.paypalservice.model.PaymentDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaypalServiceImpl implements PaypalService{
    @Value("${app.jwt.jwtSecret}")
    private String secret;

    @Override
    public TransactionResponse makePayment(PaymentDTO paymentDTO, String authHeader) {
        if( !authHeader.equals(secret)){
            return new TransactionResponse("Invalid authorization", "UNAUTHORIZED");
        }
        if(paymentDTO.getAmount() == null || paymentDTO.getOrderId() == null){
            log.info("Payment is not made by Paypal service");
            return new TransactionResponse("Payment is unsuccessful by Paypal service", "REJECTED");
        }else
            log.info("Payment is made by Paypal service");
            return new TransactionResponse("Payment is made by Paypal service", "APPROVED");

    }
}
