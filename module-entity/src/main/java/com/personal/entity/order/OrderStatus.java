package com.personal.entity.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    PENDING(Status.PENDING),
    APPROVED(Status.APPROVED),
    CANCELLED(Status.CANCELLED),
    PROCESSING(Status.PROCESSING),
    SHIPPING(Status.SHIPPING),
    COMPLETED(Status.COMPLETED);

    private final String status; // 각 enum 값에 연결된 문자열 상태

    public static OrderStatus of(String status) {
        return Arrays.stream(OrderStatus.values())
                .filter(r -> r.name().equalsIgnoreCase(status))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 OrderStatus"));
    }

    public static class Status {
        public static final String PENDING = "PENDING";
        public static final String APPROVED = "APPROVED";
        public static final String CANCELLED = "CANCELLED";
        public static final String PROCESSING = "PROCESSING";
        public static final String SHIPPING = "SHIPPING";
        public static final String COMPLETED = "COMPLETED";
    }
}
