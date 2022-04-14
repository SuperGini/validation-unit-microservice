package com.gini.validationunit.service.services.wishlist.user;

import com.gini.validationunit.dto.request.user.UserRequest;
import com.gini.validationunit.dto.response.user.UserResponse;
import org.springframework.transaction.annotation.Transactional;

public interface UserWishListService {
    @Transactional
    UserResponse saveUser(UserRequest userRequest);
}
