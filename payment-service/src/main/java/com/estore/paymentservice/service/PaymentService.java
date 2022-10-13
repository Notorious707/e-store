package com.estore.paymentservice.service;

import com.estore.paymentservice.entity.Payment;
import com.estore.paymentservice.model.PaymentDTO;

public interface PaymentService {
    void save(PaymentDTO paymentDTO);

    Payment getPayment(Long orderid);
}
