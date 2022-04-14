package com.gini.validationunit.repository.user;

import com.gini.validationunit.domaine.postgress.User;

import java.util.Optional;

public interface UserWishListRepository {
    void saveUser(User user);

    Optional<User> findByUsername(String username);
}
