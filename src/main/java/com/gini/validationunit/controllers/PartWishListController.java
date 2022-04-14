package com.gini.validationunit.controllers;

import com.gini.validationunit.dto.response.part.PartWishResponse;
import com.gini.validationunit.service.services.wishlist.part.PartWishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class PartWishListController {


    private final PartWishListService partWishListService;

    @PostMapping("/wishParts/{partNumber}/{username}")
    public ResponseEntity<?> addPartToWishlist(@PathVariable String partNumber, @PathVariable String username) {

        partWishListService.addPartToWishlist(partNumber, username);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/wishParts/{username}")
    public ResponseEntity<Set<PartWishResponse>> findAllPartsForUser(@PathVariable String username) {
        Set<PartWishResponse> part = partWishListService.findAllPatsForUser(username);

        return new ResponseEntity<>(part, HttpStatus.OK);
    }


}
