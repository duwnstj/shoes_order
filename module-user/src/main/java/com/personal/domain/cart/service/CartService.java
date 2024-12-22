package com.personal.domain.cart.service;

import com.personal.common.entity.AuthUser;
import com.personal.domain.cart.dto.CartRequest;
import com.personal.domain.cart.dto.CartResponse;
import com.personal.domain.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartResponse.GetCart getCarts(AuthUser authUser) {
        return null;
    }

    public void addCart(AuthUser authUser , Long storeId , CartRequest.WriteCart writeCart) {

    }

    public void modCart(AuthUser authUser , Long storeId , Long cartId , CartRequest.WriteCart writeCart) {

    }

    public void removeCart(AuthUser authUser , Long storeId , Long cartId) {

    }

    public void emptyCart(AuthUser authUser) {

    }
}
