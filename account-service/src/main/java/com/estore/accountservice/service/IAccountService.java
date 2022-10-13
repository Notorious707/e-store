package com.estore.accountservice.service;

import com.estore.accountservice.model.Account;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    Account findById(Long id);
    List<Account> findAll();
    Account update(Long id,Account account);
    Account create(Account account);
}
