package com.gini.validationunit.config.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Logger;
import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

/**
 * Feign will treat any 3xx status as errors -> bug
 **/
@RequiredArgsConstructor
public class FeignClientConfiguration {

    @Value("${basic.auth.username}")
    private String username;

    @Value("${basic.auth.password}")
    private String password;



    private final ObjectMapper objectMapper;

    private static final int CONNECT_TIMOUT = 15;
    private static final int READ_TIMEOUT = 15;

    @Bean
    public ErrorDecoder errorDecoder(){
        return new FeignErrorDecoder(objectMapper);
    }

    @Bean
    public Request.Options connectAndReadTimeouts() {
        return new Request.Options(
                CONNECT_TIMOUT, TimeUnit.SECONDS,
                READ_TIMEOUT, TimeUnit.SECONDS,
                true);
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(username, password);
    }

}
