package com.gini.validationunit.validation.validators;

import com.gini.validationunit.dto.request.PriceRequest;
import com.gini.validationunit.validation.annotation.ValidPrice;
import com.gini.validationunit.validation.utility.AcceptedCurrency;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class PriceValidation implements ConstraintValidator<ValidPrice, PriceRequest> {


    @Override
    public boolean isValid(PriceRequest priceRequest, ConstraintValidatorContext context) {

        BigDecimal partPrice;
        try {
            partPrice = new BigDecimal(priceRequest.getPrice());
        } catch (NumberFormatException e) {
            setViolationConstraintMessage(context, "wrong price format");
            return false;
        } catch (NullPointerException e) {
            setViolationConstraintMessage(context, "price must not be null");
            return false;
        }

        if(partPrice.compareTo(BigDecimal.ZERO) <= 0){
            setViolationConstraintMessage(context, "Price must not be zero or lower");
            return false;
        }

        if(AcceptedCurrency.getCurrenciesList().contains(priceRequest.getCurrency())){
            CurrencyUnit currency = Monetary.getCurrency(priceRequest.getCurrency());

            int currencyDigits =  currency.getDefaultFractionDigits();

            if(partPrice.scale() > currencyDigits){
                setViolationConstraintMessage(context,"no more thant 2 decimals are accepted");
                return false;
            }

        }

        return true;
    }

    private void setViolationConstraintMessage(ConstraintValidatorContext context, String constraintViolationMessage) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(constraintViolationMessage)
                .addConstraintViolation();
    }
}
