package com.personal.domain.user.dto;

public sealed interface UserAddressResponse permits
        UserAddressResponse.UserAddress
{
    record UserAddress(
            boolean repYN,
            String zip,
            String address,
            String addressDetail
    ) implements UserAddressResponse {
    }
}
