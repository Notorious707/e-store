package com.estore.paypalservice.controller;

import com.estore.paypalservice.Model.PaymentDTO;
import com.estore.paypalservice.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paypal")
public class PaypalController {
    @Autowired
    PaypalService paypalService;

    @PostMapping("/pay")
    public String makePayment(@RequestBody PaymentDTO paymentDTO){
        return paypalService.makePayment(paymentDTO);
    }
}
