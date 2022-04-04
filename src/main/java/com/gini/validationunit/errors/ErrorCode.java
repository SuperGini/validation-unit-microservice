package com.gini.validationunit.errors;

import lombok.Getter;

@Getter
public enum ErrorCode {

    VALIDATION_ERROR("validation error"),
    INVALID_FORMAT("invalid format");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
