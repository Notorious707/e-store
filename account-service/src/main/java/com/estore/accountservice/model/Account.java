package com.estore.accountservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Embedded
    private Address shippingAddress;
    @OneToOne(cascade = CascadeType.ALL)
    private PaymentMethod preferredPaymentMethod;
    @Temporal(value = TemporalType.TIMESTAMP)
    protected Date createDate = new Date();
}
