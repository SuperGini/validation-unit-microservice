package com.gini.validationunit.repository.part;

import com.gini.validationunit.domaine.Part;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

public interface PartWishListRepository {
    void savePart(Part part);

    Optional<String> findPartNumber(String username, String partNumber);

    Set<Part> findAllParts(String username);

    int updatePricesOfPart(BigDecimal priceEURO, BigDecimal priceUSD, BigDecimal priceRON, String partNumber);
}
