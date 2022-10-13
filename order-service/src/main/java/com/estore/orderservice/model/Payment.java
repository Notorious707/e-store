package com.estore.orderservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
public class Payment {
    private Long id;
    private Long orderId;
    private Double amount;
    private PaymentType paymentType;
    private String paymentStatus;

    public Payment(Long orderId, Double amount, PaymentType paymentType, String paymentStatus) {
        this.orderId = orderId;
        this.amount = amount;
        this.paymentType = paymentType;
        this.paymentStatus = paymentStatus;
    }
}
