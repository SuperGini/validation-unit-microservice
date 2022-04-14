package com.gini.validationunit.convertor;

import com.gini.validationunit.domaine.postgress.Part;
import com.gini.validationunit.dto.response.part.FindPartWithCurrencyResponse;
import com.gini.validationunit.dto.response.part.PartWishResponse;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class PartConvertor {


    public Part convertToPart(FindPartWithCurrencyResponse partResponse) {
        return Part.builder()
                .partId(partResponse.getId().toString())
                .partName(partResponse.getPartName())
                .partNumber(partResponse.getPartNumber())
                .manufacturer(partResponse.getManufacturer())
                .priceRON(new BigDecimal(partResponse.getPriceRON()))
                .priceEURO(new BigDecimal(partResponse.getPriceEURO()))
                .priceUSD(new BigDecimal(partResponse.getPriceUSD()))
                .build();
    }

    public PartWishResponse convertToPartWishResponse(Part part){
        return PartWishResponse.builder()
                .id(part.getId())
                .partId(part.getPartId())
                .partName(part.getPartName())
                .partNumber(part.getPartNumber())
                .manufacturer(part.getManufacturer())
                .priceRON(part.getPriceRON())
                .priceEURO(part.getPriceEURO())
                .priceUSD(part.getPriceUSD())
                .build();



    }

}
