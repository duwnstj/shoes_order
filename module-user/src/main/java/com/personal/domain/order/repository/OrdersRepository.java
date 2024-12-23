package com.personal.domain.order.repository;

import com.personal.entity.order.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
