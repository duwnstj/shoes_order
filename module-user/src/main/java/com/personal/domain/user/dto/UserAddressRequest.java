package com.personal.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public sealed interface UserAddressRequest permits
        UserAddressRequest.UserAddress,
        UserAddressRequest.RepYN
{
    record UserAddress(
            @NotBlank
            String zip,
            @NotBlank
            String address,
            @NotBlank
            String addressDetail
    ) implements UserAddressRequest {
    }

    record RepYN(
            @NotNull
            Long userAddressId
    ) implements UserAddressRequest {
    }
}
