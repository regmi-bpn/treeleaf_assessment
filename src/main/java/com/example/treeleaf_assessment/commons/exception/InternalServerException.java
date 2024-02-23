package com.example.treeleaf_assessment.commons.exception;

import org.springframework.http.HttpStatus;

public class InternalServerException extends BaseException {

    public InternalServerException(String code, String message) {
        super(code, message);
    }

    public InternalServerException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
