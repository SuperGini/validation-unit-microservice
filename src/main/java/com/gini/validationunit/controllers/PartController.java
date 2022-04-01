package com.gini.validationunit.controllers;

import com.gini.validationunit.dto.request.PartRequest;
import com.gini.validationunit.dto.response.ListPartsResponse;
import com.gini.validationunit.dto.response.PartResponse;
import com.gini.validationunit.service.PartService;
import com.gini.validationunit.service.feign.FeignClientCentralUnit;
import feign.ResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v3")
public class PartController {

    private final PartService partService;


    @PostMapping("/parts")
    public ResponseEntity<PartResponse> createPart(@RequestBody PartRequest partRequest) {

        PartResponse response = partService.createPart(partRequest);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/parts/{pageNumber}")
    public ResponseEntity<List<ListPartsResponse>> findAllPartsWithPagination (@PathVariable String pageNumber){

        List<ListPartsResponse> partsList = partService.findAllPartsWithPagination(pageNumber);

        return new ResponseEntity<>(partsList, HttpStatus.OK);
    }


    @GetMapping("/parts/count")
    public ResponseEntity<Integer> findPartsCount(){
        Integer partCount = partService.findPartsCount();

        return new ResponseEntity<>(partCount, HttpStatus.OK);

    }


}
