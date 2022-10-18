package com.estore.paymentservice.service;

import com.estore.paymentservice.entity.Payment;
import com.estore.paymentservice.model.PaymentDTO;
import com.estore.paymentservice.model.PaymentResponse;
import com.estore.paymentservice.model.PaymentType;
import com.estore.paymentservice.model.ValidateDTO;
import com.estore.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
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
    @Value("${app.jwt.secret}")
    private String innerCommunicationToken;
    public String makePayment(PaymentDTO paymentDTO, String authHeader, HttpServletResponse httpServletResponse) throws IOException {

        ValidateDTO validateDTO = tokenService.validateToken(authHeader);
        if (validateDTO == null || !validateDTO.isSuccess()){
            httpServletResponse.sendError(401,"UNAUTHORIZED");
         }
        Payment payment = getPayment(paymentDTO.getOrderId());
        if(payment!=null){
            httpServletResponse.sendError(400,"ALREADY PROCESSED");
            return "Error";
        }
        HttpHeaders innerCommunicationHeaders = new HttpHeaders();
        innerCommunicationHeaders.set(HttpHeaders.AUTHORIZATION, innerCommunicationToken);
        HttpEntity<PaymentDTO> innerRequestEntity = new HttpEntity<PaymentDTO>(paymentDTO,innerCommunicationHeaders);
        if(paymentDTO.getPaymentType().equals(PaymentType.Paypal)){
            ResponseEntity<PaymentResponse> paymentResponse= paypalServiceApi.exchange("/paypal/pay",HttpMethod.POST, innerRequestEntity,PaymentResponse.class);
            setPaymentId(paymentDTO, authHeader,paymentResponse);
            return paymentResponse.getBody().getMessage();
        }else if(paymentDTO.getPaymentType().equals(PaymentType.Credit)){
            ResponseEntity<PaymentResponse> paymentResponse= creditServiceApi.exchange("/credit/pay", HttpMethod.POST, innerRequestEntity,PaymentResponse.class);
            setPaymentId(paymentDTO,authHeader,paymentResponse);
            return paymentResponse.getBody().getMessage();
        }else if(paymentDTO.getPaymentType().equals(PaymentType.BankAccount)){
            ResponseEntity<PaymentResponse> paymentResponse = bankAccountServiceApi.exchange("/bankaccount/pay",HttpMethod.POST, innerRequestEntity,PaymentResponse.class);
            setPaymentId(paymentDTO,authHeader,paymentResponse);
            return paymentResponse.getBody().getMessage();
        }
        return "Unsuccessful attempt!";
    }

   private void setPaymentId(PaymentDTO paymentDTO,String authorizationHeader, ResponseEntity<PaymentResponse> paymentResponse){
       HttpHeaders headers = new HttpHeaders();
       HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
       headers.set(HttpHeaders.AUTHORIZATION, authorizationHeader);
       paymentDTO.setPaymentStatus(paymentResponse.getBody().getMessage());
       save(paymentDTO);
       Payment payment = getPayment(paymentDTO.getOrderId());
       if(payment!=null){
           orderServiceApi.exchange("/orders/set-payment-id/"+paymentDTO.getOrderId()+"/"+payment.getId(), HttpMethod.POST,requestEntity,Object.class);
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
