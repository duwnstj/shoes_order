package com.personal.domain.order.dto;

import com.personal.entity.order.OrderStatus;

import java.time.LocalDate;
import java.util.List;

public sealed interface OrderResponse permits
        OrderResponse.Infos,
        OrderResponse.Info
{
    record Infos(
            Long orderId,
            String orderNo,
            LocalDate orderDate,
            String recipi,
            String tel,
            String request,
            String zip,
            String address,
            String addressDetail,
            OrderStatus orderStatus,
            Long totalAmt,
            Long totalTax,
            Long detailCnt
    ) implements OrderResponse {
    }

    record Info(
            Long orderId,
            String orderNo,
            LocalDate orderDate,
            String recipi,
            String tel,
            String request,
            String zip,
            String address,
            String addressDetail,
            OrderStatus orderStatus,
            Long totalAmt,
            Long totalTax,
            List<OrderDetail.Detail> detailList
    ) implements OrderResponse {
    }
}
