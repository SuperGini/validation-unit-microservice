package com.gini.validationunit.dto.request.part;


import com.gini.validationunit.validation.annotation.NotNullEmptyOrBlank;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CarModelRequest {

    @NotNullEmptyOrBlank
    private String constructor;

    @NotNullEmptyOrBlank
    private String model;

    private String year;

    private String engine;

}
