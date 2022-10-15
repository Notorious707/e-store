package com.estore.accountservice.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountSignInRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

}


