package com.darmokhval.crm_programming_school.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotEmpty(message = "Email shouldn't be empty!")
    @Email(message = "Invalid email pattern!")
    private String email;
    @NotEmpty(message = "Password shouldn't be empty!")
    private String password;
}
