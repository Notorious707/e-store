package com.estore.accountservice.controller;

import com.estore.accountservice.model.*;
import com.estore.accountservice.security.jwt.JwtUtils;
import com.estore.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private AccountService accountService;

    @GetMapping("/{id}")
    public Account findById(@PathVariable("id") Long id){
        return accountService.findById(id);
    }
    @GetMapping
    public List<Account> findAll(){
        return  accountService.findAll();
    }
    @PutMapping("/update/{id}")
    public Account update(@PathVariable("id") Long id,@RequestBody Account account){
        return accountService.update(id,account);
    };
    @PostMapping("/add-payment-method/{id}")
    public Account addPaymentMethod(@PathVariable("id") Long id,@RequestBody PaymentMethod paymentMethod)
    {
        Account account=accountService.findById(id);
        account.setPreferredPaymentMethod(paymentMethod);
        return accountService.create(account);
    }
}
