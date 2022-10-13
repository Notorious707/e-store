package com.estore.creditservice.service;

import com.estore.creditservice.model.CreditDTO;

public interface CreditService {
    String makePayment(CreditDTO creditDTO);
}
