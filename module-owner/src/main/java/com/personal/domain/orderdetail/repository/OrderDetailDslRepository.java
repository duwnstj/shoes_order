package com.personal.domain.orderdetail.repository;

import com.personal.domain.orderdetail.dto.OrderDetailRequest;
import com.personal.domain.orderdetail.dto.OrderDetailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderDetailDslRepository {
    Page<OrderDetailResponse.GetInfos> getOrders(Pageable pageable, Long storeId, OrderDetailRequest.GetOrders getOrders);
}
