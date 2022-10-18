package com.estore.paymentservice.service;

import com.estore.paymentservice.entity.Payment;
import com.estore.paymentservice.model.*;
import com.estore.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    RestTemplate orderServiceApi;

    @Autowired
    RestTemplate paypalServiceApi;

    @Autowired
    RestTemplate creditServiceApi;
    @Autowired
    RestTemplate bankAccountServiceApi;
    @Autowired
    private TokenService tokenService;

    @Value("${rest.api-secret}")
    private String apiSecret;
    public String makePayment(PaymentDTO paymentDTO, String authHeader, HttpServletResponse httpServletResponse) throws IOException {

        ValidateDTO validateDTO = tokenService.validateToken(authHeader);
        if (validateDTO == null || !validateDTO.isSuccess()){
            httpServletResponse.sendError(401,"UNAUTHORIZED");
         }
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION,apiSecret);

        HttpEntity<PaymentDTO> request = new HttpEntity<>(paymentDTO,headers);

        if(paymentDTO.getPaymentType().equals(PaymentType.Paypal)){
            PaymentResponse paymentResponse = paypalServiceApi.postForObject("/paypal/pay",request,PaymentResponse.class);
            paymentDTO.setPaymentStatus(paymentResponse.getStatus());
            save(paymentDTO);
            setPaymentId(paymentDTO,authHeader);
            return paymentResponse.getStatus();
        }else if(paymentDTO.getPaymentType().equals(PaymentType.Credit)){
            PaymentResponse paymentResponse = creditServiceApi.postForObject("/credit/pay",request,PaymentResponse.class);
            paymentDTO.setPaymentStatus(paymentResponse.getStatus());
            save(paymentDTO);
            setPaymentId(paymentDTO,authHeader);
            return paymentResponse.getStatus();
        }else if(paymentDTO.getPaymentType().equals(PaymentType.BankAccount)){
            PaymentResponse paymentResponse = bankAccountServiceApi.postForObject("/bankaccount/pay",request,PaymentResponse.class);
            paymentDTO.setPaymentStatus(paymentResponse.getStatus());
            save(paymentDTO);
            setPaymentId(paymentDTO,authHeader);
            return paymentResponse.getStatus();
        }
        return "Unsuccessful attempt!";
    }

   private void setPaymentId(PaymentDTO paymentDTO,String authHeader){
        Payment payment = getPayment((paymentDTO.getOrderId()));
        HttpHeaders header = new HttpHeaders();
        header.set(HttpHeaders.AUTHORIZATION,authHeader);

       PaymentIdSetDTO paymentIdSetDTO = new PaymentIdSetDTO(paymentDTO.getOrderId(),payment.getId());

       HttpEntity<PaymentIdSetDTO> request = new HttpEntity<>(paymentIdSetDTO,header);

       if(payment!=null){
           orderServiceApi.postForObject("/orders/set-payment-id/"+paymentDTO.getOrderId()+"/"+payment.getId(),request,Object.class);
       }
   }
    @Override
    public void save(PaymentDTO paymentDTO) {
        Payment payment = new Payment(paymentDTO.getOrderId(),paymentDTO.getAmount(),paymentDTO.getPaymentType(),paymentDTO.getPaymentStatus());
        paymentRepository.save(payment);
    }

    @Override
    public Payment getPayment(Long orderid){
        Optional<Payment> optionalPayment = paymentRepository.findByOrderId(orderid);
        return optionalPayment.orElse(null);
    }


}
