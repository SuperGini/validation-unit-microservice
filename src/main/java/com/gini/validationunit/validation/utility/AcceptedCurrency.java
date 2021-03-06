package com.gini.validationunit.validation.utility;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum AcceptedCurrency {

    EUR,
    USD,
    RON;


    public static List<String> getCurrenciesList(){
        return Stream.of(AcceptedCurrency.values())
                .map(Enum::name)
                .toList();
    }

}
