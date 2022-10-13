package com.estore.creditservice.service;

import com.estore.creditservice.model.CreditDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@Service
public class CreditServiceImpl implements CreditService{

    @Override
    public String makePayment(CreditDTO creditDTO) {
        if(creditDTO.getAmount() == null || creditDTO.getOrderId() == null){
            log.info("Payment made by Credit service");
            return "Payment Unsuccessful by Credit Account";
        }else
            log.info("Payment can be made by Credit service");
        return "Payment Successfully made by Credit Account";
    }
}
