package com.estore.paymentservice.controller;

import com.estore.paymentservice.entity.Payment;
import com.estore.paymentservice.model.PaymentDTO;
import com.estore.paymentservice.model.PaymentType;
import com.estore.paymentservice.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpResponse;

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
            String paymentResponse = restTemplate.postForObject("http://localhost:8087/credit/pay",paymentDTO,String.class);
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
    @GetMapping("/getpayment/{orderid}")
    public ResponseEntity<Payment> getPayment(@PathVariable Long orderid){
        Payment payment = paymentService.getPayment(orderid);
        if(payment!=null){
            return new ResponseEntity<Payment>(payment,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
