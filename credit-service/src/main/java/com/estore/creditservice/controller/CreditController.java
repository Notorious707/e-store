package com.estore.creditservice.controller;

import com.estore.creditservice.model.CreditDTO;
import com.estore.creditservice.model.TransactionResponse;
import com.estore.creditservice.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit")
public class CreditController {
    @Autowired
    CreditService creditService;

    @PostMapping("/pay")
    public TransactionResponse makePayment(@RequestBody CreditDTO creditDTO, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization ){
        return creditService.makePayment(creditDTO,authorization);
    }
}

