package com.personal.domain.orderdetail.controller;

import com.personal.common.entity.AuthUser;
import com.personal.common.entity.SuccessResponse;
import com.personal.domain.orderdetail.dto.OrderDetailRequest;
import com.personal.domain.orderdetail.dto.OrderDetailResponse;
import com.personal.domain.orderdetail.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
public class OrderDetailController {
    private final OrderDetailService orderService;

    /**
     * 주문 상세 조회
     * @param storeId
     * @param authUser
     * @param getOrders startDate,endDate,orderStatus
     * @return
     */
    @GetMapping("/{storeId}/orders")
    public ResponseEntity<SuccessResponse<Page<OrderDetailResponse.GetInfos>>> getOrders(
            @PathVariable Long storeId,
            @AuthenticationPrincipal AuthUser authUser,
            @ModelAttribute OrderDetailRequest.GetOrders getOrders
    ) {
        return ResponseEntity.ok()
                .body(SuccessResponse.of(orderService.getOrders(storeId, authUser, getOrders)));
    }


}
