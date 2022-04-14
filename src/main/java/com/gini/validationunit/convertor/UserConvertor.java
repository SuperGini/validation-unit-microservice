package com.gini.validationunit.convertor;

import com.gini.validationunit.domaine.postgress.User;
import com.gini.validationunit.dto.request.user.UserRequest;
import com.gini.validationunit.dto.response.user.UserResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserConvertor {

    public User convertToUser(UserRequest userRequest){
        User user = new User();
        user.setUsername(userRequest.username());

        return user;
    }


    public UserResponse toUserResponse(User user){
       return new UserResponse(user.getId(), user.getUsername());

    }



}
