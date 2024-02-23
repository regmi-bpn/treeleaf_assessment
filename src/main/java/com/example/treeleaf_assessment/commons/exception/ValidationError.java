package com.example.treeleaf_assessment.commons.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ValidationError {
    private final String field;
    private final String message;
}
