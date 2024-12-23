package com.personal.domain.order.dto;

public sealed interface OrderDetail permits
        OrderDetail.Detail
{
    record Detail(
        Long id,
        Long productId,
        String productName,
        Long length,
        Long width,
        Long qty,
        boolean customYN,
        Long customPrice,
        Long basePrice,
        Long amt,
        Long tax
    ) implements OrderDetail {
    }
}
