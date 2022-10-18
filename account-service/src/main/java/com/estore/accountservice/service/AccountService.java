package com.estore.accountservice.service;

import com.estore.accountservice.exception.NotFoundException;
import com.estore.accountservice.model.Account;
import com.estore.accountservice.model.ValidateResponse;
import com.estore.accountservice.model.roles.ERole;
import com.estore.accountservice.model.roles.Role;
import com.estore.accountservice.payload.request.AccountSignInRequest;
import com.estore.accountservice.payload.request.AccountSignUpRequest;
import com.estore.accountservice.payload.response.AccountResponse;
import com.estore.accountservice.payload.response.MessageResponse;
import com.estore.accountservice.repository.AccountRepository;
import com.estore.accountservice.repository.RoleRepository;
import com.estore.accountservice.security.jwt.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountService implements IAccountService{
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    PasswordEncoder encoder;

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

    @Override
    public ResponseEntity<?> signUp(AccountSignUpRequest accountSignUpRequest) {
        if(accountRepository.existsByEmail(accountSignUpRequest.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }
        //Creating user's new account here
        Account account = new Account(accountSignUpRequest.getFirstName(),accountSignUpRequest.getLastName(),
                accountSignUpRequest.getEmail(),encoder.encode(accountSignUpRequest.getPassword()),
                accountSignUpRequest.getShippingAddress());


        // Setting roles here
        Set<String> strRoles = accountSignUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        account.setRoles(roles);

        accountRepository.save(account);

        return ResponseEntity.ok(new MessageResponse("You are successfully signed up"));
    }

    @Override
    public ResponseEntity<?> authenticateUser(AccountSignInRequest accountSignInRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(accountSignInRequest.getEmail(), accountSignInRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new AccountResponse(jwt,
                userDetails.getId(),
                userDetails.getEmail(),
                roles));
    }

    @Override
    public ResponseEntity<ValidateResponse> validate(String authorizationHeader) {
        String token = null;
        try {
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
            token = authorizationHeader.substring(7);

            Claims claims = jwtUtils.getClaims(token);
            if(claims!=null){
                ValidateResponse validateResponse = new ValidateResponse(true,"Validated!");
                return new ResponseEntity<>(validateResponse,HttpStatus.OK);
            }

        }
        }catch (Exception ignored){}
        ValidateResponse response = new ValidateResponse(false,"NotValidated!");
        return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);

    }


}
