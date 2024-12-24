package com.personal.domain.store.dto;

import jakarta.validation.constraints.NotBlank;

public sealed interface StoreRequest permits
   StoreRequest.SaveStores
    {
        record SaveStores(
                @NotBlank
                String name,
                @NotBlank
                String tel,
                @NotBlank
                String zip,
                @NotBlank
                String address,
                @NotBlank
                String addressDetail,
                @NotBlank
                String description
                ) implements StoreRequest {
        }
    }

