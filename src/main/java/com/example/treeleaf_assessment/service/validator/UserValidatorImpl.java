package com.example.treeleaf_assessment.service.validator;

import com.example.treeleaf_assessment.commons.exception.BadRequestException;
import com.example.treeleaf_assessment.entity.user.User;
import com.example.treeleaf_assessment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserValidatorImpl implements UserValidator {

    private final UserRepository userRepository;

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new BadRequestException("UV001", "Id Not Found!!"));

    }

    @Override
    public User validateUserByEmail(String email) {
        return userRepository.validateUserByEmail(email.toLowerCase()).orElseThrow(() -> new BadRequestException("UV002", "Email Not Found!!"));
    }
}
