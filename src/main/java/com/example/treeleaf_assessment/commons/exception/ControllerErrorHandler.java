package com.example.treeleaf_assessment.commons.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerErrorHandler extends ResponseEntityExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(ControllerErrorHandler.class);


    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(Exception e, HttpServletRequest request) {
        BaseException exception = new ForbiddenException(ErrorCodes.RESOURCE_NOT_FOUND, "The resource doesn't exists or you aren't authorized to access this resource.");
        this.addPathToErrorResponse(exception, request);
        return ResponseEntity.status(exception.getHttpStatus()).body(exception.getErrorResponse());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {
        log.error("Exception caught on base controller ", e);
        BaseException baseException;
        if (e instanceof BaseException) baseException = (BaseException) e;
        else
            baseException = new InternalServerException(ErrorCodes.INTERNAL_SERVER_ERROR, "Something went wrong. Please try again later.");
        this.addPathToErrorResponse(baseException, request);
        return ResponseEntity.status(baseException.getHttpStatus()).body(baseException.getErrorResponse());
    }

    private void addPathToErrorResponse(BaseException baseException, HttpServletRequest request) {
        baseException.setPath(request.getRequestURI());
    }
}