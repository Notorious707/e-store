package com.estore.bankaccountservice.controller;

import com.estore.bankaccountservice.model.BankAccountDTO;
import com.estore.bankaccountservice.model.TransactionResponse;
import com.estore.bankaccountservice.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bankaccount")
public class BankAccountController {
    @Autowired
    BankAccountService bankAccountService;
    @PostMapping("/pay")
    public TransactionResponse makePayment(@RequestBody BankAccountDTO bankAccountDTO, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization){
        return bankAccountService.makePayment(bankAccountDTO,authorization);
    }
}
