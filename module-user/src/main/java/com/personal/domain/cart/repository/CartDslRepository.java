package com.personal.domain.cart.repository;

import com.personal.domain.cart.dto.CartList;

import java.util.List;

public interface CartDslRepository {
    List<CartList.Cart> getCarts(Long userId);
}
