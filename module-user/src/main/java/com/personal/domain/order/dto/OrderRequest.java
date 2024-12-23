package com.personal.domain.order.dto;

import java.time.LocalDate;

public sealed interface OrderRequest permits
        OrderRequest.GetOrder
{
    record GetOrder(
            LocalDate startDate,
            LocalDate endDate,
            String status
    ) implements OrderRequest {
    }
}
