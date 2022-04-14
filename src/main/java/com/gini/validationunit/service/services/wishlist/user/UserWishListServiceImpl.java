package com.gini.validationunit.service.services.wishlist.user;

import com.gini.validationunit.convertor.UserConvertor;
import com.gini.validationunit.domaine.User;
import com.gini.validationunit.dto.request.user.UserRequest;
import com.gini.validationunit.dto.response.user.UserResponse;
import com.gini.validationunit.errors.exception.UserAlreadyExistsException;
import com.gini.validationunit.errors.exception.UserNotFoundException;
import com.gini.validationunit.repository.user.UserWishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserWishListServiceImpl implements UserWishListService {

    private final UserWishListRepository userRepository;


    @Override
    @Transactional
    public UserResponse saveUser(UserRequest userRequest){

        checkIfUserExists(userRequest);
        User user = UserConvertor.convertToUser(userRequest);
        userRepository.saveUser(user);
        User savedUser = findSavedUser(user);

        return UserConvertor.toUserResponse(savedUser);
    }


    private User findSavedUser(User user) {
        return userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> {
                    throw new UserNotFoundException("user not found");
                });
    }


    private void checkIfUserExists(UserRequest userRequest) {
        userRepository.findByUsername(userRequest.username())
                .ifPresent(x -> {throw new UserAlreadyExistsException("User already exist");});
    }


}
