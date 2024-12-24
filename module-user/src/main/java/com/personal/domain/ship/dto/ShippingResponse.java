package com.personal.domain.ship.dto;

import com.personal.entity.ship.ShipStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public sealed interface ShippingResponse permits
        ShippingResponse.Info
{
    record Info(
            ShipStatus shippingStatus,
            LocalDate shipStartDate,
            LocalDate shipCompleteDate,
            String trackingNumber,
            String carrier,
            String trackingUrl,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) implements ShippingResponse {
    }
}
