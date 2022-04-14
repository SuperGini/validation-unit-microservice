package com.gini.validationunit.service.services.wishlist.part;

import com.gini.validationunit.dto.response.part.PartWishResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface PartWishListService {
    @Transactional
    PartWishResponse addPartToWishlist(String partNumber, String username);

    Set<PartWishResponse> findAllPatsForUser(String username);
}
