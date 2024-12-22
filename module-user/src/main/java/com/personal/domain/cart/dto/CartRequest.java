package com.personal.domain.cart.dto;

import jakarta.validation.constraints.NotNull;

public sealed interface CartRequest permits
    CartRequest.WriteCart
{
    record WriteCart(
            @NotNull
            Long productId,
            Long length,
            Long width,
            @NotNull
            Long qty,
            boolean customYN
    ) implements CartRequest {
    }
}
