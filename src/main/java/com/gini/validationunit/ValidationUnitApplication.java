package com.gini.validationunit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@ConfigurationPropertiesScan("com.gini") // -> scan for @ConfigurationProperties files and need a dependecy in POM too
public class ValidationUnitApplication {

    public static void main(String[] args) {
        SpringApplication.run(ValidationUnitApplication.class, args);
    }

}
