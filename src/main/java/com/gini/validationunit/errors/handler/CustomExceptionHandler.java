package com.gini.validationunit.errors.handler;


import com.gini.validationunit.errors.ErrorCode;
import com.gini.validationunit.errors.ErrorResponse;
import com.gini.validationunit.errors.exception.InventoryClientException;
import com.gini.validationunit.errors.exception.InventoryServerException;
import com.gini.validationunit.errors.exception.UserAlreadyExistsException;
import com.gini.validationunit.errors.exception.UserNotFoundException;
import com.gini.validationunit.errors.response.RestErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

        //i keep this method because is gooooooooooooooooooooood:D!!!!!
//    @ExceptionHandler(FeignException.BadRequest.class)
//    public Map<String, Object> handleFeignStatusException(FeignException e, HttpServletResponse response) {
//        response.setStatus(e.status());
//        return new JSONObject(e.contentUTF8()).toMap();
//    }

    @ExceptionHandler(InventoryClientException.class)
    public ResponseEntity<RestErrorResponse> handleWarehouseException(InventoryClientException e) {
        log.warn("Error coming from warehouse -> errorCode: {} -- errorMessage: {} -- errors: {}", e.getErrorCode(), e.getErrorMessage(), e.getErrors(), e);
        RestErrorResponse response = new RestErrorResponse(
                e.getErrorCode(),
                e.getErrorMessage(),
                e.getErrors()
        );

        return new ResponseEntity<>(response, HttpStatus.valueOf(e.getStatus()));
    }

    @ExceptionHandler(InventoryServerException.class)
    public ResponseEntity<RestErrorResponse> handleServerErrors(InventoryServerException e){
        log.error("Error coming from warehouse: ", e);
        RestErrorResponse response = new RestErrorResponse(
                e.getError(),
                e.getMessage(),
                List.of() //just something here
        );

        return new ResponseEntity<>(response, HttpStatus.valueOf(e.getStatus()));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<RestErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException e){
        log.error("Error user already exists: ", e);
        RestErrorResponse response = new RestErrorResponse(
                ErrorCode.USER_ALREADY_EXISTS.toString(),
                ErrorCode.USER_ALREADY_EXISTS.getMessage(),
                List.of()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<RestErrorResponse> handleUserNotFoundException(UserNotFoundException e){
        log.error("Error user already exists: ", e);
        RestErrorResponse response = new RestErrorResponse(
                ErrorCode.USER_NOT_FOUND.toString(),
                ErrorCode.USER_NOT_FOUND.getMessage(),
                List.of()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        List<ErrorResponse> errors = new ArrayList<>();

        ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .filter(distinctByKey(p -> p.getField()))
                .forEach( error -> addErrorToErrorsList(errors, error));


        var restResponse = new RestErrorResponse(
                ErrorCode.VALIDATION_ERROR.toString(),
                ErrorCode.VALIDATION_ERROR.getMessage(),
                errors
        );

        return ResponseEntity.badRequest().body(restResponse);
    }


    private void addErrorToErrorsList(List<ErrorResponse> errors, FieldError x) {
        String field = x.getField();
        String message = x.getDefaultMessage();
        ErrorResponse errorResponse = new ErrorResponse(field, message);
        errors.add(errorResponse);
    }

   //todo: https://stackoverflow.com/questions/23699371/java-8-distinct-by-property
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        final Set<Object> seen = new HashSet<>();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
