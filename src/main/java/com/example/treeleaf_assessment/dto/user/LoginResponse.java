package com.example.treeleaf_assessment.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {

    private final String message;
    private final String token;
}
