package com.gini.validationunit.controllers;

import com.gini.validationunit.dto.request.user.UserRequest;
import com.gini.validationunit.dto.response.user.UserResponse;
import com.gini.validationunit.service.services.postgres.user.UserWishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class UserController {


    private final UserWishListService userService;


    @PostMapping("/users")
    public ResponseEntity<?> saveUser(@RequestBody UserRequest userRequest) {

        UserResponse userResponse = userService.saveUser(userRequest);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);

    }

}
