package com.estore.paymentservice.service;

import com.estore.paymentservice.entity.Payment;
import com.estore.paymentservice.model.PaymentDTO;
import com.estore.paymentservice.model.PaymentType;
import com.estore.paymentservice.model.ValidateDTO;
import com.estore.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String makePayment(PaymentDTO paymentDTO, String authHeader, HttpServletResponse httpServletResponse) throws IOException {

        ValidateDTO validateDTO = tokenService.validateToken(authHeader);
        if (validateDTO == null || !validateDTO.isSuccess()){
            httpServletResponse.sendError(401,"UNAUTHORIZED");
         }

        if(paymentDTO.getPaymentType().equals(PaymentType.Paypal)){
            String paymentResponse = paypalServiceApi.postForObject("/paypal/pay",paymentDTO,String.class);
            paymentDTO.setPaymentStatus(paymentResponse);
            save(paymentDTO);
            setPaymentId(paymentDTO);
            return paymentResponse;
        }else if(paymentDTO.getPaymentType().equals(PaymentType.Credit)){
            String paymentResponse = creditServiceApi.postForObject("/credit/pay",paymentDTO,String.class);
            paymentDTO.setPaymentStatus(paymentResponse);
            save(paymentDTO);
            setPaymentId(paymentDTO);
            return paymentResponse;
        }else if(paymentDTO.getPaymentType().equals(PaymentType.BankAccount)){
            String paymentResponse = bankAccountServiceApi.postForObject("/bankaccount/pay",paymentDTO,String.class);
            paymentDTO.setPaymentStatus(paymentResponse);
            save(paymentDTO);
            setPaymentId(paymentDTO);
            return paymentResponse;
        }
        return "Unsuccessful attempt!";
    }

   private void setPaymentId(PaymentDTO paymentDTO){
       Map<String,Long> body= new HashMap<String,Long>();
       Payment payment = getPayment(paymentDTO.getOrderId());
       if(payment!=null){
           orderServiceApi.postForObject("/orders/set-payment-id/"+paymentDTO.getOrderId()+"/"+payment.getId(),body,Object.class);
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
