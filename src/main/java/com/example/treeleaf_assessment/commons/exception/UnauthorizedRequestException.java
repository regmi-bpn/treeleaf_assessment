package com.example.treeleaf_assessment.commons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class UnauthorizedRequestException extends BaseException {
    public UnauthorizedRequestException(String code, String message) {
        super(code, message);
    }

    public UnauthorizedRequestException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    @Override
    public HttpStatusCode getHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
