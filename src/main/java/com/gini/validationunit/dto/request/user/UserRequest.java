package com.gini.validationunit.dto.request.user;

import javax.validation.constraints.NotNull;

public record UserRequest(

        @NotNull(message = "username must not be null")
        String username
) {
}
