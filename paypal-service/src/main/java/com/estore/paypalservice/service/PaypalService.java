package com.estore.paypalservice.service;

import com.estore.paypalservice.Model.PaymentDTO;

public interface PaypalService {
    String makePayment(PaymentDTO paymentDTO);
}
