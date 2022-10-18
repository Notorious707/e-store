package com.estore.bankaccountservice.service;

import com.estore.bankaccountservice.model.BankAccountDTO;
import com.estore.bankaccountservice.model.TransactionResponse;

public interface BankAccountService {

    TransactionResponse makePayment(BankAccountDTO bankAccountDTO, String authorizationHeader);
}
