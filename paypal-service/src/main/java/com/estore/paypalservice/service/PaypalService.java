package com.estore.paypalservice.service;

import com.estore.paypalservice.model.TransactionResponse;
import com.estore.paypalservice.model.PaymentDTO;

public interface PaypalService {
    TransactionResponse makePayment(PaymentDTO paymentDTO,String authHeader);
}
