package com.gini.validationunit.validation.annotation;

import com.gini.validationunit.validation.validators.PriceValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PriceValidation.class)
public @interface ValidPrice {

    String message() default "bla bla bla";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
