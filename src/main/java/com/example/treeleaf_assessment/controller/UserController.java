package com.example.treeleaf_assessment.controller;

import com.example.treeleaf_assessment.dto.user.LoginRequest;
import com.example.treeleaf_assessment.dto.user.LoginResponse;
import com.example.treeleaf_assessment.dto.Message;
import com.example.treeleaf_assessment.dto.user.RegisterRequest;
import com.example.treeleaf_assessment.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public Message register(@Valid @RequestBody RegisterRequest request) {
        log.info("Register :: {}", request);
        return userService.register(request);
    }

    @PostMapping(value = "/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        log.info("Login :: {}", request);
        return userService.login(request);
    }
}
