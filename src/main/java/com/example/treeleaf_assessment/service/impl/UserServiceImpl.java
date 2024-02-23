package com.example.treeleaf_assessment.service.impl;

import com.example.treeleaf_assessment.commons.SecurityUtil;
import com.example.treeleaf_assessment.commons.config.JwtTokenUtil;
import com.example.treeleaf_assessment.commons.exception.BadRequestException;
import com.example.treeleaf_assessment.dto.user.LoginRequest;
import com.example.treeleaf_assessment.dto.user.LoginResponse;
import com.example.treeleaf_assessment.dto.Message;
import com.example.treeleaf_assessment.dto.user.RegisterRequest;
import com.example.treeleaf_assessment.entity.user.User;
import com.example.treeleaf_assessment.repository.UserRepository;
import com.example.treeleaf_assessment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public Message register(RegisterRequest request) {
        Optional<User> users = userRepository.validateUserByEmail(request.getEmail().toLowerCase());
        if (users.isPresent()) throw new BadRequestException("USR003", "The provided email is already present.");
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail().toLowerCase());
        validatePasswordAndConfirmPassword(request.getPassword(), request.getConfirmPassword());
        user.setPassword(SecurityUtil.encode(request.getPassword()));
        userRepository.save(user);
        return Message.builder()
                .message("Signed Up Successfully.")
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Optional<User> users = userRepository.validateUserByEmail(request.getEmail().toLowerCase());
        if (users.isEmpty()) throw new BadRequestException("USR002", "The provided login credentials are invalid.");
        User user = users.get();
        if (!user.getPassword().equals(SecurityUtil.encode(request.getPassword()))) {
            throw new BadRequestException("USR002", "The provided login credentials are invalid.");
        }
        return getLoginResponse(user, "Logged in successfully.");
    }


    private LoginResponse getLoginResponse(User user, String message) {
        return LoginResponse.builder()
                .message(message)
                .token(getToken(user))
                .build();
    }

    private String getToken(User user) {
        Map<String, Object> preparedClaims = prepareClaims(user.getEmail(), user.getId(), "USER");
        return jwtTokenUtil.generateToken(preparedClaims);
    }

    private Map<String, Object> prepareClaims(String userName, String id, String userType) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("userName", userName);
        claims.put("userType", userType);
        return claims;
    }

    private void validatePasswordAndConfirmPassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword))
            throw new BadRequestException("USR001", "The password and confirm password does not match.");
    }
}
