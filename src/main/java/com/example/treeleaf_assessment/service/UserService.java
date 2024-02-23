package com.example.treeleaf_assessment.service;

import com.example.treeleaf_assessment.dto.user.LoginRequest;
import com.example.treeleaf_assessment.dto.user.LoginResponse;
import com.example.treeleaf_assessment.dto.Message;
import com.example.treeleaf_assessment.dto.user.RegisterRequest;

public interface UserService {

    LoginResponse login(LoginRequest request);

    Message register(RegisterRequest request);
}
