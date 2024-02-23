package com.example.treeleaf_assessment.commons.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends BaseException {

    public ForbiddenException(String code, String message) {
        super(code, message);
    }

    public ForbiddenException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.FORBIDDEN;
    }
}
