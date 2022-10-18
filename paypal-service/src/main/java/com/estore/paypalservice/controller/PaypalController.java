package com.estore.paypalservice.controller;

import com.estore.paypalservice.Model.TransactionResponse;
import com.estore.paypalservice.model.PaymentDTO;
import com.estore.paypalservice.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paypal")
public class PaypalController {
    @Autowired
    PaypalService paypalService;

    @PostMapping("/pay")
    public TransactionResponse makePayment(@RequestBody PaymentDTO paymentDTO, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){
        return paypalService.makePayment(paymentDTO,authorizationHeader);
    }
}
