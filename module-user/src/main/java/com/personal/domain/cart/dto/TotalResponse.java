package com.personal.domain.cart.dto;

public sealed interface TotalResponse permits
        TotalResponse.TotalAmt
{
    record TotalAmt(
        Long totalAmt,
        Long totalTax
    ) implements TotalResponse {
    }
}
