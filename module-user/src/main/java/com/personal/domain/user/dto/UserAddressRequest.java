package com.personal.domain.user.dto;

import jakarta.validation.constraints.NotBlank;

public sealed interface UserAddressRequest permits
        UserAddressRequest.UserAddress
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
}
