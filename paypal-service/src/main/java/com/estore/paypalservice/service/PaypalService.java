package com.estore.paypalservice.service;

import com.estore.paypalservice.model.PaymentDTO;

public interface PaypalService {
    String makePayment(PaymentDTO paymentDTO);
}
