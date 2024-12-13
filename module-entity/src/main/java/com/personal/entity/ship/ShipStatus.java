package com.personal.entity.ship;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ShipStatus {
    SHIPPINGSTARTED("배송 시작"),
    INTRANSIT("배송 중"),
    DELIVERED("배송 완료");

    private final String status;

}
