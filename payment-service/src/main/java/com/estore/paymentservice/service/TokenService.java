package com.estore.paymentservice.service;

import com.estore.paymentservice.model.ValidateDTO;


public interface TokenService {
    ValidateDTO validateToken(String authorizationHeader);
}
