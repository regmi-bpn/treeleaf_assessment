package com.example.treeleaf_assessment.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @Email
    @NotNull(message = "Email must not be null.")
    private String email;

    @NotNull(message = "Password must not be null.")
    private String password;
}
