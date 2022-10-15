package com.estore.accountservice.payload.response;

import com.estore.accountservice.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class AccountResponse {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String email;
    private List<String> roles;

    public AccountResponse(String token, Long id, String email, List<String> roles) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.roles = roles;
    }
}

