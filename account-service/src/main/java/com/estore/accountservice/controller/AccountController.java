package com.estore.accountservice.controller;

import com.estore.accountservice.exception.NotFoundException;
import com.estore.accountservice.model.*;
import com.estore.accountservice.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {
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
    @PutMapping("/{id}")
    public Account update(@PathVariable("id") Long id,@RequestBody Account account){
        return accountService.update(id,account);
    };
    @PostMapping
    public Account create(@RequestBody Account account){
        return accountService.create(account);
    }
    @PostMapping("/add-payment-method/{id}")
    public Account addPaymentMethod(@PathVariable("id") Long id,@RequestBody PaymentMethod paymentMethod)
    {
        Account account=accountService.findById(id);
        account.setPreferredPaymentMethod(paymentMethod);
        return accountService.create(account);
    }
}
