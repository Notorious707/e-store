package com.estore.accountservice.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreditCard.class, name = "credit-card"),
        @JsonSubTypes.Type(value = BankAccount.class, name = "bank-account"),
        @JsonSubTypes.Type(value = Paypal.class, name = "paypal")
})
public class PaymentMethod {
    @Id
    private long id;
    @OneToOne
    private Account account;
    @Temporal(value = TemporalType.TIMESTAMP)
    protected Date createDate = new Date();
}
