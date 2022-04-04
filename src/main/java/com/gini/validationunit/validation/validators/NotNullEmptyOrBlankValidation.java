package com.gini.validationunit.validation.validators;

import com.gini.validationunit.validation.annotation.NotNullEmptyOrBlank;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotNullEmptyOrBlankValidation implements ConstraintValidator<NotNullEmptyOrBlank, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !StringUtils.isBlank(value);
    }
}
