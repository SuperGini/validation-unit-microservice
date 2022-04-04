package com.gini.validationunit.validation.annotation;

import com.gini.validationunit.validation.validators.NotNullEmptyOrBlankValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = NotNullEmptyOrBlankValidation.class)
public @interface NotNullEmptyOrBlank {

    String message() default "must not be null, empty or blank";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
