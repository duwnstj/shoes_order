package com.personal.domain.order.repository;

import com.personal.entity.order.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> , OrdersDslRepository {
    Optional<Orders> findByIdAndUserId(Long id, Long userId);
}
