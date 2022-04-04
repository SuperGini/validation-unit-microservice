package com.gini.validationunit.dto.request;


import com.gini.validationunit.validation.annotation.ValidCurrency;
import com.gini.validationunit.validation.annotation.ValidPrice;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ValidPrice
public class PriceRequest {

    @NotNull
    private String price;

    @NotNull
    @ValidCurrency
    private String currency;
}
