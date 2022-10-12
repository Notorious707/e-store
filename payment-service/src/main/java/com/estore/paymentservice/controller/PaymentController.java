package com.estore.paymentservice.controller;

import com.estore.paymentservice.entity.Payment;
import com.estore.paymentservice.model.PaymentDTO;
import com.estore.paymentservice.model.PaymentType;
import com.estore.paymentservice.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/")
    public String makePayment(@RequestBody PaymentDTO paymentDTO){
        if(paymentDTO.getPaymentType().equals(PaymentType.Paypal)){
            String paymentResponse = restTemplate.postForObject("http://localhost:8084/paypal/pay",paymentDTO,String.class);
            paymentDTO.setPaymentStatus(paymentResponse);
            paymentService.save(paymentDTO);
            return paymentResponse;
        }else if(paymentDTO.getPaymentType().equals(PaymentType.Credit)){
            String paymentResponse = restTemplate.postForObject("http://localhost:8084/paypal/pay",paymentDTO,String.class);
            paymentDTO.setPaymentStatus(paymentResponse);
            paymentService.save(paymentDTO);
            return paymentResponse;
        }else if(paymentDTO.getPaymentType().equals(PaymentType.BankAccount)){
            String paymentResponse = restTemplate.postForObject("http://localhost:8086/bankaccount/pay",paymentDTO,String.class);
            paymentDTO.setPaymentStatus(paymentResponse);
            paymentService.save(paymentDTO);
            return paymentResponse;
        }
          return "Unsuccessful attempt!";
    }

}
