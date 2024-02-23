package com.example.treeleaf_assessment.commons.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private final String code;
    private final String message;
    private final String statusCode;
    private final String time;
    private final Object errors;
    @Setter
    private String path;
}
