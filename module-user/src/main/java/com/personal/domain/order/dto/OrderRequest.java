package com.personal.domain.order.dto;

import java.time.LocalDate;
import java.util.Objects;

public sealed interface OrderRequest permits
        OrderRequest.Order,
        OrderRequest.GetOrder
{
    record Order(
            String tel,
            String request
    ) implements OrderRequest {
    }

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
