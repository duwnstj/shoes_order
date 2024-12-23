package com.personal.domain.order.service;

import com.personal.common.entity.AuthUser;
import com.personal.domain.order.dto.OrderDetail;
import com.personal.domain.order.dto.OrderRequest;
import com.personal.domain.order.dto.OrderResponse;
import com.personal.domain.order.repository.OrdersRepository;
import com.personal.entity.order.Orders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrdersService {
    private final OrdersCommonService ordersCommonService;
    private final OrderDetailService orderDetailService;
    private final OrdersRepository ordersRepository;

    @Transactional
    public void orders(AuthUser authUser) {
        // 내 장바구니가 맞는지? (장바구니가 비어있는지도 확인)


        // 장바구니 조회(현 시점 가격 포함)


        // 장바구니 조회한 데이터를 기반으로 데이터 가공
        // 개별 가격은 어떻고? 총 가격은 어떤지?!


        // OrderNO 채번


        // OrderDate 오늘 날짜


        // Orders에 저장


        // OrdersDetail에 저장

    }

    public Page<OrderResponse.Infos> getOrders(AuthUser authUser , OrderRequest.GetOrder getOrder) {
        Pageable pageable = PageRequest.of(getOrder.page() - 1, getOrder.size());
        return ordersRepository.getOrders(authUser.getUserId(), getOrder, pageable);
    }

    public OrderResponse.Info getOrder(AuthUser authUser , Long orderId) {
        Orders orders = ordersCommonService.getOrderByOrdersIdAndUserId(orderId, authUser.getUserId());
        List<OrderDetail.Detail> ordersDetails = orderDetailService.getOrderDetail(orderId);
        return new OrderResponse.Info(
                orders.getId(),
                orders.getOrderNo(),
                orders.getOrderDate(),
                orders.getRecipi(),
                orders.getTel(),
                orders.getRequest(),
                orders.getZip(),
                orders.getAddress(),
                orders.getAddressDetail(),
                orders.getOrderStatus(),
                orders.getTotalAmt(),
                orders.getTotalTax(),
                ordersDetails);
    }
}
