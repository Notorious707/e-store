package com.estore.paymentservice.service;

import com.estore.paymentservice.entity.Payment;
import com.estore.paymentservice.model.PaymentDTO;
import com.estore.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    PaymentRepository paymentRepository;
    @Override
    public void save(PaymentDTO paymentDTO) {
        Payment payment = new Payment(paymentDTO.getOrderId(),paymentDTO.getAmount(),paymentDTO.getPaymentType(),paymentDTO.getPaymentStatus());
        paymentRepository.save(payment);
    }
}
