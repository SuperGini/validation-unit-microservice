package com.gini.validationunit.service.feign;


import com.gini.validationunit.dto.request.PartRequest;
import com.gini.validationunit.dto.response.PartResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "${centralUnit.name}", url = "${centralUnit.url}", configuration = FeignClientConfiguration.class)
public interface FeignClientCentralUnit {


    @PostMapping("/v2/parts")
    ResponseEntity<PartResponse> createPart(@RequestBody PartRequest request);

}
