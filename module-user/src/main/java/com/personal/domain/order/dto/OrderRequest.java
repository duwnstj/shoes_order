package com.personal.domain.order.dto;

import java.time.LocalDate;
import java.util.Objects;

public sealed interface OrderRequest permits
        OrderRequest.GetOrder
{
    record GetOrder(
            LocalDate startDate,
            LocalDate endDate,
            String status,
            Integer page,
            Integer size
    ) implements OrderRequest {
        public GetOrder {
            if(Objects.isNull(page)) page = 1;
            if(Objects.isNull(size)) size = 10;
        }
    }
}
