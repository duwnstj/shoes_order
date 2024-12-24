package com.personal.domain.cart.dto;

import java.util.List;

public sealed interface CartResponse permits
        CartResponse.GetCart
{
    record GetCart(
        List<CartList.Cart> list,
        TotalResponse.TotalAmt total
    ) implements CartResponse {
    }
}
