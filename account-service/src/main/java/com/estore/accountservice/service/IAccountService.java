package com.estore.accountservice.service;

import com.estore.accountservice.model.Account;
import com.estore.accountservice.model.ValidateResponse;
import com.estore.accountservice.payload.request.AccountSignInRequest;
import com.estore.accountservice.payload.request.AccountSignUpRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    Account findById(Long id);
    List<Account> findAll();
    Account update(Long id,Account account);
    Account create(Account account);


    ResponseEntity<?> signUp(AccountSignUpRequest request);

    ResponseEntity<?> authenticateUser(AccountSignInRequest accountSignInRequest);


    ResponseEntity<ValidateResponse> validate(String authorizationHeader);
}
