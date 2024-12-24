package com.personal.domain.owner.dto;

public sealed interface OwnerResponse permits
        OwnerResponse.GetProfile {
    record GetProfile(
            String email,
            String name,
            String address,
            String addressDetail
    ) implements OwnerResponse {
    }
}
