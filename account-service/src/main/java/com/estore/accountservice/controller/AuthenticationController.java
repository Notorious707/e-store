package com.estore.accountservice.controller;
import com.estore.accountservice.model.ValidateResponse;
import com.estore.accountservice.payload.request.AccountSignInRequest;
import com.estore.accountservice.payload.request.AccountSignUpRequest;
import com.estore.accountservice.security.jwt.JwtUtils;
import com.estore.accountservice.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private AccountService accountService;


    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody AccountSignUpRequest request){
        log.info("Request received: {}", request);
        return accountService.signUp(request);

    }
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AccountSignInRequest accountSignInRequest) {
        return accountService.authenticateUser(accountSignInRequest);
    }
    @GetMapping("/validate")
    public ResponseEntity<ValidateResponse> validate(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){
        return accountService.validate(authorizationHeader);
    }
}
