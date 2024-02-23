package com.example.treeleaf_assessment.service.validator;

import com.example.treeleaf_assessment.entity.user.User;

public interface UserValidator {

    User getUserById(String id);

    User validateUserByEmail(String email);
}
