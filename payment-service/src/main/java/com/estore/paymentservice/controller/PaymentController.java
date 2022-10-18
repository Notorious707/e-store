package com.estore.paymentservice.controller;

import com.estore.paymentservice.entity.Payment;
import com.estore.paymentservice.model.PaymentDTO;
import com.estore.paymentservice.model.PaymentType;
import com.estore.paymentservice.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    RestTemplate orderServiceApi;

    @Autowired
    RestTemplate paypalServiceApi;

    @Autowired
    RestTemplate creditServiceApi;
    @Autowired
    RestTemplate bankAccountServiceApi;
    @PostMapping("/")
    public String makePayment(@RequestBody PaymentDTO paymentDTO, @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader, HttpServletResponse httpServletResponse) throws IOException {
        return paymentService.makePayment(paymentDTO, authHeader,httpServletResponse);
    }
}
