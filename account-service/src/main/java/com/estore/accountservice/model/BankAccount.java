package com.estore.accountservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
public class BankAccount extends PaymentMethod{
    private String firstName;
    private String lastName;
    private String phoneNumber;

    private String accountNumber;
    private String routingNumber;
}
