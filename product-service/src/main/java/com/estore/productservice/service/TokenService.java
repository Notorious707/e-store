package com.estore.productservice.service;

import com.estore.productservice.model.ProductResponse;
import com.estore.productservice.model.ValidateDTO;


public interface TokenService {
    ProductResponse validateToken(String authorizationHeader);
}
