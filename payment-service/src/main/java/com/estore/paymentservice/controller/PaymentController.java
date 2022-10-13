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
    public String makePayment(@RequestBody PaymentDTO paymentDTO){
        if(paymentDTO.getPaymentType().equals(PaymentType.Paypal)){
            String paymentResponse = paypalServiceApi.postForObject("/paypal/pay",paymentDTO,String.class);
            paymentDTO.setPaymentStatus(paymentResponse);
            Map<String,Long> body= new HashMap<String,Long>();
            paymentService.save(paymentDTO);
            Payment payment = paymentService.getPayment(paymentDTO.getOrderId());
            if(payment!=null){
                orderServiceApi.postForObject("/orders/set-payment-id/"+paymentDTO.getOrderId()+"/"+payment.getId(),body,Object.class);
            }
            return paymentResponse;
        }else if(paymentDTO.getPaymentType().equals(PaymentType.Credit)){
            String paymentResponse = creditServiceApi.postForObject("/credit/pay",paymentDTO,String.class);
            paymentDTO.setPaymentStatus(paymentResponse);
            paymentService.save(paymentDTO);
            return paymentResponse;
        }else if(paymentDTO.getPaymentType().equals(PaymentType.BankAccount)){
            String paymentResponse = bankAccountServiceApi.postForObject("/bankaccount/pay",paymentDTO,String.class);
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
            return new ResponseEntity<>(payment, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
