package com.personal.domain.order.repository;

import com.personal.entity.order.OrdersDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrdersDetail, Long> {
    @Query("select od from OrdersDetail od where od.orders.id = :orderId")
    List<OrdersDetail> getOrderDetailByOrderId(Long orderId);
}
