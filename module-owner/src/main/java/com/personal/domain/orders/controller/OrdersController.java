package com.personal.domain.orders.controller;

import com.personal.common.entity.AuthUser;
import com.personal.common.entity.SuccessResponse;
import com.personal.domain.orders.dto.OrdersRequest;
import com.personal.domain.orders.dto.OrdersResponse;
import com.personal.domain.orders.service.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores/{storeId}/orders")
@RestController
public class OrdersController {
    private final OrdersService ordersService;

    @GetMapping
    public ResponseEntity<SuccessResponse<Page<OrdersResponse.OrdersInfo>>> getOrders(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long storeId,
            @ModelAttribute OrdersRequest.GetOrders getOrders
    ) {
        return ResponseEntity.ok().body(SuccessResponse.of(ordersService.getOrders(authUser, storeId, getOrders)));
    }
}
