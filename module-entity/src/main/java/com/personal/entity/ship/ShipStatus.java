package com.personal.entity.ship;

import com.personal.entity.order.OrderStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ShipStatus {
    SHIPPINGSTARTED(Status.SHIPPINGSTARTED),
    INTRANSIT(Status.INTRANSIT),
    DELIVERED(Status.DELIVERED);

    private final String status;

    public static ShipStatus of(String status) {
        return Arrays.stream(ShipStatus.values())
                .filter(r -> r.name().equalsIgnoreCase(status))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 ShipStatus"));
    }

    public static class Status {
        public static final String SHIPPINGSTARTED = "SHIPPINGSTARTED";
        public static final String INTRANSIT = "INTRANSIT";
        public static final String DELIVERED = "DELIVERED";
    }
}
