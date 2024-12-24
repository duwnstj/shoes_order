package com.personal.domain.ship.dto;

import java.time.LocalDate;
import java.util.Objects;

public sealed interface ShippingRequest permits
        ShippingRequest.GetShipping
{
    record GetShipping(
        LocalDate startDate,
        LocalDate endDate,
        Integer page,
        Integer size
    ) implements ShippingRequest {
        public GetShipping {
            if(Objects.isNull(page)) page = 1;
            if(Objects.isNull(size)) size = 10;
        }
    }
}
