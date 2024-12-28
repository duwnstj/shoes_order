package com.personal.domain.orderdetail.repository;


import com.personal.entity.order.OrdersDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrdersDetail, Long>,OrderDetailDslRepository {
}
