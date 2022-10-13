package com.estore.creditservice.controller;

import com.estore.creditservice.model.CreditDTO;
import com.estore.creditservice.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credit")
public class CreditController {
    @Autowired
    CreditService creditService;

    @PostMapping("/pay")
    public String makePayment(@RequestBody CreditDTO creditDTO){
        return creditService.makePayment(creditDTO);
    }
}

