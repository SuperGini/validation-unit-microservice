package com.gini.validationunit.dto.response.part;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.gini.validationunit.dto.response.currency.api.CurrencyErrorResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FindPartWithCurrencyResponse {

    private UUID id;
    private String partName;
    private Integer partCount;
    private String partNumber;
    private BigDecimal price;
    private String currency;
    private String manufacturer;

    private String priceRON;
    private String priceEURO;
    private String priceUSD;

    private CurrencyErrorResponse currencyErrorResponse;


}
