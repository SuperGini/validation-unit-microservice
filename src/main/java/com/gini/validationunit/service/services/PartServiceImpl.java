package com.gini.validationunit.service.services;

import com.gini.validationunit.dto.request.PartRequest;
import com.gini.validationunit.dto.request.UpdatePartRequest;
import com.gini.validationunit.dto.response.FindPartWithCurrencyResponse;
import com.gini.validationunit.dto.response.ListPartsResponse;
import com.gini.validationunit.dto.response.PartResponse;
import com.gini.validationunit.service.PartService;
import com.gini.validationunit.service.feign.FeignClientCentralUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PartServiceImpl implements PartService {


    private final FeignClientCentralUnit feignClientCentralUnit;

    @Override
    public PartResponse createPart(PartRequest request) {
        return feignClientCentralUnit.createPart(request).getBody();
    }

    @Override
    public List<ListPartsResponse> findAllPartsWithPagination(String pageNumber) {
        return feignClientCentralUnit.findAllPartsWithPagination(pageNumber).getBody();
    }

    @Override
    public Integer findPartsCount() {
        return feignClientCentralUnit.findPartsCount().getBody();
    }

    @Override
    public Integer updatePart(UpdatePartRequest updatePartRequest) {
        return feignClientCentralUnit.updatePart(updatePartRequest).getBody();
    }

    @Override
    public FindPartWithCurrencyResponse findPartByPartNumber(String partNumber) {


        var findPartResponse = feignClientCentralUnit.findPartByPartNumber(partNumber).getBody();

        if(findPartResponse.getCurrencyErrorResponse().getError() == null){
            findPartResponse.setCurrencyErrorResponse(null);
        }


        return findPartResponse;
    }


}
