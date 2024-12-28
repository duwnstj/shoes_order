package com.personal.domain.orderdetail.dto;

import com.personal.entity.order.OrderStatus;

import java.time.LocalDate;

public sealed interface OrderDetailRequest permits
        OrderDetailRequest.GetOrders{
    record GetOrders(
            Integer page,
            Integer size,
            String sort,
            LocalDate startDate,
            LocalDate endDate,
            OrderStatus orderStatus
    )
            implements OrderDetailRequest {

    }

}
