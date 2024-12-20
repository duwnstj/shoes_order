package com.personal.domain.user.dto;

import jakarta.validation.constraints.NotBlank;

public sealed interface UserRequest permits
        UserRequest.Login ,
        UserRequest.Register ,
        UserRequest.UpdateProfile
{
    record Login(
            @NotBlank
            String email,
            @NotBlank
            String password
    ) implements UserRequest {
    }

    record Register(
            @NotBlank
            String email,
            @NotBlank
            String password,
            @NotBlank
            String name
    ) implements UserRequest {
    }

    record UpdateProfile(
            @NotBlank
            String password,
            @NotBlank
            String name
    ) implements  UserRequest {
    }
}
