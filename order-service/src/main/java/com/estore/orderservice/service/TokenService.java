package com.estore.orderservice.service;

import com.estore.orderservice.model.ValidateDTO;


public interface TokenService {
    ValidateDTO validateToken(String authorizationHeader);
}
