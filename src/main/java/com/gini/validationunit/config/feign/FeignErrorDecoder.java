package com.gini.validationunit.config.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gini.validationunit.errors.exception.InventoryClientException;
import com.gini.validationunit.errors.exception.InventoryServerException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static feign.FeignException.errorStatus;

@Slf4j
public record FeignErrorDecoder(ObjectMapper objectMapper) implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        HttpStatus httpStatus =  HttpStatus.valueOf(response.status());

        if (httpStatus.is4xxClientError()) {
            try {
                String errorMessage = IOUtils.toString(response.body().asInputStream(), String.valueOf(StandardCharsets.UTF_8));

                InventoryClientException clientException = objectMapper.readValue(errorMessage, InventoryClientException.class);
                clientException.setStatus(response.status());

                throw clientException;
            } catch (IOException e) {
                log.error("Error reading client error message from inventory: ", e);
                //:todo -> throw exception and shit
            }
        }

        if (httpStatus.is5xxServerError()) {
            try {
                String errorMessage = IOUtils.toString(response.body().asInputStream(), String.valueOf(StandardCharsets.UTF_8));

                InventoryServerException serverException = objectMapper.readValue(errorMessage, InventoryServerException.class);
                serverException.setStatus(response.status());

                return serverException;
            } catch (IOException e) {
                log.error("Error reading server error message from inventory: ", e);
                //:todo -> throw exception and shit
            }
        }

        return errorStatus(methodKey, response);
    }
}
