package com.estore.bankaccountservice.service;

import com.estore.bankaccountservice.model.BankAccountDTO;

public interface BankAccountService {

    String makePayment(BankAccountDTO bankAccountDTO);
}
