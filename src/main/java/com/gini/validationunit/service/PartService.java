package com.gini.validationunit.service;

import com.gini.validationunit.dto.request.part.PartRequest;
import com.gini.validationunit.dto.request.part.UpdatePartRequest;
import com.gini.validationunit.dto.response.part.FindPartWithCurrencyResponse;
import com.gini.validationunit.dto.response.part.ListPartsResponse;
import com.gini.validationunit.dto.response.part.PartResponse;

import java.util.List;

public interface PartService {
    PartResponse createPart(PartRequest request);

    List<ListPartsResponse> findAllPartsWithPagination(String pageNumber);

    Integer findPartsCount();

    Integer updatePart(UpdatePartRequest updatePartRequest);

    FindPartWithCurrencyResponse findPartByPartNumber(String partNumber);
}
