package com.estore.orderservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "estore_orders")
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

    @Transient
    private Double amount;

    public Order(Long accountId, List<OrderItem> items, Address shippingAddress, PaymentType paymentType, Long paymentId, Payment payment, Double amount) {
        this.accountId = accountId;
        this.items = items;
        this.shippingAddress = shippingAddress;
        this.paymentType = paymentType;
        this.paymentId = paymentId;
        this.amount = amount;
    }
}
