package com.example.treeleaf_assessment.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    @NotNull(message = "Name must not be null.")
    private String name;

    @NotNull(message = "Email must not be null.")
    @Email
    private String email;

    @NotNull(message = "Password must not be null.")
    private String password;

    @NotNull(message = "Password must not be null.")
    private String confirmPassword;
}
