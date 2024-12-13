package com.personal.entity.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    PENDING("대기"),
    APPROVED("승인"),
    CANCELLED("취소"),
    PROCESSING("생산"),
    SHIPPING("배송"),
    COMPLETED("완료");

    private final String status; // 각 enum 값에 연결된 문자열 상태
}
