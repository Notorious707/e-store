package com.estore.accountservice.model;

import com.estore.accountservice.model.roles.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateResponse {
    private boolean success;
    private String message;
}
