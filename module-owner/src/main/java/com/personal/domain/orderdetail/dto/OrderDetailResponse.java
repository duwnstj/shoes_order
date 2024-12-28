package com.personal.domain.orderdetail.dto;

import com.personal.entity.order.OrderStatus;

import java.time.LocalDate;

public sealed interface OrderDetailResponse permits
        OrderDetailResponse.GetInfos {
    record GetInfos(
            //orders
            Long storeProductCnt,
            Long orderId,
            Long storeId,
            String storeName,
            Long orderNo,
            LocalDate orderDate,
            String recipi,
            String tel,
            String request,
            OrderStatus orderStatus,
            String paymentMethod,
            Long totalAmt,
            Long totalTax,
            String zip,
            String address,
            String addressDetail,
            //ordersDetail
            Long productId,
            String productName,
            Long length,
            Long width,
            Long qty,
            Boolean customYN,
            Long customPrice,
            Long basePrice,
            Long amt,
            Long tax

    ) implements OrderDetailResponse {
    }
}
