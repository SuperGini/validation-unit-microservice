package com.gini.validationunit.dto.response.part;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class PartWishResponse {

    private Long id;
    private String partId;
    private String partName;
    private String partNumber;
    private String manufacturer;

    private BigDecimal priceRON;
    private BigDecimal priceEURO;
    private BigDecimal priceUSD;


}
