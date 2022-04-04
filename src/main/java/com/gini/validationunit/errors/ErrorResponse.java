package com.gini.validationunit.errors;

public record ErrorResponse(
        String field,
        String message
) {
}
