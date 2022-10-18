package com.estore.paymentservice.service;

import com.estore.paymentservice.entity.Payment;
import com.estore.paymentservice.model.PaymentDTO;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface PaymentService {
    void save(PaymentDTO paymentDTO);
    

    Payment getPayment(Long orderid);

    String makePayment(PaymentDTO paymentDTO, String authHeader, HttpServletResponse httpServletResponse) throws IOException;
}
