package com.estore.bankaccountservice.controller;

import com.estore.bankaccountservice.model.BankAccountDTO;
import com.estore.bankaccountservice.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bankaccount")
public class BankAccountController {
    @Autowired
    BankAccountService bankAccountService;
    @PostMapping("/pay")
    public String makePayment(@RequestBody BankAccountDTO bankAccountDTO){
        return bankAccountService.makePayment(bankAccountDTO);
    }
}
