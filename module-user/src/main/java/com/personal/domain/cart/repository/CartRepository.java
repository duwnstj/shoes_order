package com.personal.domain.cart.repository;

import com.personal.entity.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> , CartDslRepository {
    List<Cart> findByUserIdAndStoreId(Long userId, Long storeId);
    Optional<Cart> findByIdAndUserIdAndStoreId(Long id, Long userId, Long storeId);
    void deleteByUserId(Long userId);
}
