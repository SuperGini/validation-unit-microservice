package com.gini.validationunit.service;

import com.gini.validationunit.dto.request.PartRequest;
import com.gini.validationunit.dto.response.ListPartsResponse;
import com.gini.validationunit.dto.response.PartResponse;

import java.util.List;

public interface PartService {
    PartResponse createPart(PartRequest request);

    List<ListPartsResponse> findAllPartsWithPagination(String pageNumber);

    Integer findPartsCount();
}
