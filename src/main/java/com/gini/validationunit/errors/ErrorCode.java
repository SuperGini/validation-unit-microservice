package com.gini.validationunit.errors;

import lombok.Getter;

@Getter
public enum ErrorCode {

    VALIDATION_ERROR("validation error"),
    INVALID_FORMAT("invalid format"),
    USER_ALREADY_EXISTS("user already exists"),
    USER_NOT_FOUND("user not found"),
    PART_IS_IN_WISHLIST("the part is already added to wishlist. Can't add the same part again");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
