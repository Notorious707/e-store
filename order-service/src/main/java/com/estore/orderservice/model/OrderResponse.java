package com.estore.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderResponse {
    private String message;
    private String status;
}
