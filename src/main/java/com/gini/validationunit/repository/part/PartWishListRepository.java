package com.gini.validationunit.repository.part;

import com.gini.validationunit.domaine.postgress.Part;

import java.util.Optional;
import java.util.Set;

public interface PartWishListRepository {
    void savePart(Part part);

    Optional<String> findPartNumber(String username, String partNumber);

    Set<Part> findAllParts(String username);
}
