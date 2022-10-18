package com.estore.creditservice.service;

import com.estore.creditservice.model.CreditDTO;
import com.estore.creditservice.model.TransactionResponse;

public interface CreditService {
    TransactionResponse makePayment(CreditDTO creditDTO,String authorization);
}
