package com.personal.domain.order.service;

import com.personal.common.entity.AuthUser;
import com.personal.domain.order.dto.OrderRequest;
import com.personal.domain.order.dto.OrderResponse;
import com.personal.domain.order.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;

    public void orders(AuthUser authUser) {

    }

    public Page<OrderResponse.Infos> getOrders(AuthUser authUser , OrderRequest.GetOrder getOrder) {
        return null;
    }

    public OrderResponse.Info getOrder(AuthUser authUser , Long orderId) {
        return null;
    }
}
