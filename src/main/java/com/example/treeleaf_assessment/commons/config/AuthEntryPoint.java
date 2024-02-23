package com.example.treeleaf_assessment.commons.config;

import com.example.treeleaf_assessment.commons.exception.BaseException;
import com.example.treeleaf_assessment.commons.exception.ErrorCodes;
import com.example.treeleaf_assessment.commons.exception.ErrorResponse;
import com.example.treeleaf_assessment.commons.exception.UnauthorizedRequestException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint, Serializable {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        BaseException exception = new UnauthorizedRequestException(ErrorCodes.UNAUTHORIZED,
                "Authorization failed or token expired");
        response.setStatus(exception.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ErrorResponse errorResponse = exception.getErrorResponse();
        errorResponse.setPath(request.getRequestURI());
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(errorResponse);
        try (ServletOutputStream stream = response.getOutputStream()) {
            stream.print(json);
            stream.flush();
        }
    }
}
