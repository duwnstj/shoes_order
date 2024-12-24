package com.personal.domain.order.repository;

import com.personal.domain.order.dto.OrderRequest;
import com.personal.domain.order.dto.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrdersDslRepository {
    Page<OrderResponse.Infos> getOrders(Long userId , OrderRequest.GetOrder getOrder , Pageable pageable);
}
