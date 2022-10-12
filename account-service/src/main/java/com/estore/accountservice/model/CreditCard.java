package com.estore.accountservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class CreditCard extends PaymentMethod{
    private String firstName;
    private String lastName;
    private String cvv;
    private String cardNumber;
    @Temporal(value = TemporalType.DATE)
    private Date expireDate;
}
