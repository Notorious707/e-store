package com.estore.shippingservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private Long accountId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderItem> items;

    @Embedded
    private Address shippingAddress;

    private OrderState status;

    @Temporal(value = TemporalType.TIMESTAMP)
    protected Date createDate = new Date();

    private PaymentType paymentType;

    private Long paymentId;
}
