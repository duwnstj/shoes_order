package com.personal.domain.user.dto;

public sealed interface UserResponse permits
        UserResponse.getProfile
{
    record getProfile(
        String email,
        String name,
        String address,
        String addressDetail
    ) implements UserResponse {
    }
}
