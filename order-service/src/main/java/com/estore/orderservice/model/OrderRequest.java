package com.estore.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderRequest {

    private Long accountId;

    private List<OrderItem> items;

    private Address shippingAddress;

    private PaymentType paymentType;

    private Long paymentId;

    private Payment payment;

    private Double amount;
}
