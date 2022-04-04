package com.gini.validationunit.dto.request;


import com.gini.validationunit.validation.annotation.NotNullEmptyOrBlank;
import com.gini.validationunit.validation.annotation.ValidPartCount;
import com.gini.validationunit.validation.order.FirstValidation;
import com.gini.validationunit.validation.order.SecondValidation;
import com.gini.validationunit.validation.order.ThirdValidation;
import lombok.Data;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@GroupSequence({
        PartRequest.class,
        FirstValidation.class,
        SecondValidation.class,
        ThirdValidation.class})
@Data
public class PartRequest {

    @NotNull(groups = FirstValidation.class)
    @NotEmpty(groups = SecondValidation.class)
    @NotBlank(groups = ThirdValidation.class)
    private String partName;

    @NotNull(groups = FirstValidation.class)
    @NotEmpty(groups = SecondValidation.class)
    @NotBlank(groups = ThirdValidation.class)
    private String partNumber;

    @ValidPartCount
    private String partCount;

    @NotNull
    @Valid //to validate the fields inside nested object we need this annotation. Otherwise it doesn't work.
    private PriceRequest partPrice;

    private CarModelRequest carModel;

    @NotNullEmptyOrBlank
    private String suplayerName;

    @NotNullEmptyOrBlank
    private String manufacturer;

}
