package com.example.treeleaf_assessment.commons.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class BadRequestException extends BaseException {

    public BadRequestException(String code, String message) {
        super(code, message);
    }

    public BadRequestException(String code, String message, List<ValidationError> errors) {
        super(code, message, errors);
    }


    public BadRequestException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
