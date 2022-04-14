package com.gini.validationunit.controllers;

import com.gini.validationunit.dto.request.part.PartRequest;
import com.gini.validationunit.dto.request.part.UpdatePartRequest;
import com.gini.validationunit.dto.response.part.FindPartWithCurrencyResponse;
import com.gini.validationunit.dto.response.part.ListPartsResponse;
import com.gini.validationunit.dto.response.part.PartResponse;
import com.gini.validationunit.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class PartController {

    private final PartService partService;


    @PostMapping("/parts")
    public ResponseEntity<PartResponse> createPart(@Valid @RequestBody PartRequest partRequest) {

        PartResponse response = partService.createPart(partRequest);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/parts/{pageNumber}")
    public ResponseEntity<List<ListPartsResponse>> findAllPartsWithPagination(@PathVariable String pageNumber) {

        List<ListPartsResponse> partsList = partService.findAllPartsWithPagination(pageNumber);

        return new ResponseEntity<>(partsList, HttpStatus.OK);
    }


    @GetMapping("/parts/count")
    public ResponseEntity<Integer> findPartsCount() {
        Integer partCount = partService.findPartsCount();

        return new ResponseEntity<>(partCount, HttpStatus.OK);
    }


    @PutMapping("/parts")
    public ResponseEntity<?> updatePart(@RequestBody UpdatePartRequest updatePartRequest) {
        Integer ok = partService.updatePart(updatePartRequest);
        return ResponseEntity.ok().body(ok);
    }


    @GetMapping("/parts/part/{partNumber}")
    public ResponseEntity<FindPartWithCurrencyResponse> findPartByPartNumber(@PathVariable String partNumber) {
        return ResponseEntity
                .ok()
                .body(partService.findPartByPartNumber(partNumber));

    }
}
