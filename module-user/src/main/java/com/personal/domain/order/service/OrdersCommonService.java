package com.personal.domain.order.service;

import com.personal.common.code.ResponseCode;
import com.personal.common.exception.custom.NotFoundException;
import com.personal.domain.order.repository.OrdersRepository;
import com.personal.entity.order.Orders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrdersCommonService {
    private final OrdersRepository ordersRepository;

    public Orders getOrderByOrdersIdAndUserId(Long ordersId, Long userId) {
        return ordersRepository.findByIdAndUserId(ordersId , userId).orElseThrow(() -> new NotFoundException(ResponseCode.NOT_FOUND_ORDERS));
    }
}
