package com.gini.validationunit.errors.exception;



import com.gini.validationunit.errors.response.InventoryErrorFields;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class InventoryClientException extends RuntimeException {

    private int status;
    private String errorCode;
    private String errorMessage;
    private List<InventoryErrorFields> errors = new ArrayList<>();

}
