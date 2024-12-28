package com.personal.domain.orders.service;

import com.personal.common.entity.AuthUser;
import com.personal.domain.orders.dto.OrdersRequest;
import com.personal.domain.orders.dto.OrdersResponse;
import com.personal.domain.orders.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;

    public Page<OrdersResponse.OrdersInfo> getOrders(AuthUser authUser , Long storeId , OrdersRequest.GetOrders getOrders) {
        Pageable pageable = PageRequest.of(getOrders.page() - 1, getOrders.size());
        return ordersRepository.getOrders(authUser.getUserId() , storeId , getOrders , pageable);
    }
}
