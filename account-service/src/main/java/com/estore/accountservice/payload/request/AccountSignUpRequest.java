package com.estore.accountservice.payload.request;

import com.estore.accountservice.model.Address;
import com.estore.accountservice.model.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
public class AccountSignUpRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    private Set<String> role;
    private Address shippingAddress;
}
