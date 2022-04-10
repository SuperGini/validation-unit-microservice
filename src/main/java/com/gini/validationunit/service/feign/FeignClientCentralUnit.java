package com.gini.validationunit.service.feign;


import com.gini.validationunit.dto.request.PartRequest;
import com.gini.validationunit.dto.request.UpdatePartRequest;
import com.gini.validationunit.dto.response.FindPartWithCurrencyResponse;
import com.gini.validationunit.dto.response.ListPartsResponse;
import com.gini.validationunit.dto.response.PartResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@FeignClient(name = "${centralUnit.name}", url = "${centralUnit.url}", configuration = FeignClientConfiguration.class)
public interface FeignClientCentralUnit {


    @PostMapping("/v1/parts")
    ResponseEntity<PartResponse> createPart(@RequestBody PartRequest request);

    @GetMapping("/v1/parts/{pageNumber}")
    ResponseEntity<List<ListPartsResponse>> findAllPartsWithPagination(@PathVariable String pageNumber);

    @GetMapping("/v1/parts/count")
    ResponseEntity<Integer> findPartsCount();

    @PutMapping("/v1/parts")
    ResponseEntity<Integer> updatePart(@RequestBody UpdatePartRequest updatePartRequest);

    @GetMapping("/v1/parts/part/{partNumber}")
    ResponseEntity<FindPartWithCurrencyResponse> findPartByPartNumber(@PathVariable String partNumber);

}
