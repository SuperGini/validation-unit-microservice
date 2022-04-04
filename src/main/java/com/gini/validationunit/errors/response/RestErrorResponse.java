package com.gini.validationunit.errors.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gini.validationunit.errors.ErrorResponse;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RestErrorResponse (
         String errorCode,
         String errorMessage,
         @JsonInclude(JsonInclude.Include.NON_EMPTY)
         List<ErrorResponse> errors
){
}
