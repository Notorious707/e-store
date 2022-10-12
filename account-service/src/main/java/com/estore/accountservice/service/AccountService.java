package com.estore.accountservice.service;

import com.estore.accountservice.exception.NotFoundException;
import com.estore.accountservice.model.Account;
import com.estore.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService{
    @Autowired
    AccountRepository accountRepository;
    @Override
    public Account findById(Long id) {
        Optional <Account> account=accountRepository.findById(id);
        if(account.isEmpty()){
            throw new NotFoundException("Account not found!");
        }
        return account.get();
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account update(Long id,Account account) {
        Account foundAccount=this.findById(id);
        foundAccount.setEmail(account.getEmail());
        foundAccount.setFirstName(account.getFirstName());
        foundAccount.setLastName(account.getLastName());
        if(account.getPreferredPaymentMethod()!=null){
            foundAccount.setPreferredPaymentMethod(account.getPreferredPaymentMethod());
        }
        if(account.getShippingAddress()!=null){
            foundAccount.setShippingAddress(account.getShippingAddress());
        }
        return accountRepository.save(foundAccount);
    }

    @Override
    public Account create(Account account) {
        accountRepository.save(account);
        return account;
    }
}
