package com.gini.validationunit.controllers;

import com.gini.validationunit.dto.request.PartRequest;
import com.gini.validationunit.dto.response.PartResponse;
import com.gini.validationunit.service.feign.FeignClientCentralUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v3")
public class PartController {

    private final FeignClientCentralUnit feignClientCentralUnit;


    @PostMapping("/parts")
    public ResponseEntity<PartResponse> createPart(@RequestBody PartRequest partRequest) {

        PartResponse response = feignClientCentralUnit.createPart(partRequest).getBody();

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    public ResponseEntity<PartResponse> errorExternal(PartRequest partRequest, Throwable throwable) {


        PartResponse response = PartResponse.builder()
                .manufacturer("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")
                .partNumber(throwable.getMessage())
                .partName(throwable.getStackTrace().toString())
                .build();


        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

}
