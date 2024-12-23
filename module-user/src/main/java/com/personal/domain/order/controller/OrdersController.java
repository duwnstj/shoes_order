package com.personal.domain.order.controller;

import com.personal.common.entity.AuthUser;
import com.personal.common.entity.SuccessResponse;
import com.personal.domain.order.dto.OrderRequest;
import com.personal.domain.order.service.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@RestController
public class OrdersController {

    private final OrdersService ordersService;

    /**
     * 주문하기
     * */
    @PostMapping
    public ResponseEntity<SuccessResponse<Void>> orders(
            @AuthenticationPrincipal AuthUser authUser
    ) {
        return null;
    }

    /**
     * 주문 다건 조회
     * */
    @GetMapping
    public ResponseEntity<SuccessResponse<Void>> getOrders(
            @AuthenticationPrincipal AuthUser authUser ,
            @ModelAttribute OrderRequest.GetOrder getOrder
    ) {
        return null;
    }

    /**
     * 주문 상세 조회
     * */
    @GetMapping("/{orderId}")
    public ResponseEntity<SuccessResponse<Void>> getOrders32(
            @AuthenticationPrincipal AuthUser authUser ,
            @PathVariable Long orderId
    ) {
        return null;
    }
}
