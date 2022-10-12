package com.estore.paymentservice.entity;

import com.estore.paymentservice.model.PaymentType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue
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
