package com.personal.domain.order.service;

import com.personal.domain.order.dto.OrderDetail;
import com.personal.domain.order.repository.OrderDetailRepository;
import com.personal.entity.order.OrdersDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    public List<OrderDetail.Detail> getOrderDetail(Long orderId) {
        List<OrdersDetail> ordersDetails = orderDetailRepository.getOrderDetailByOrderId(orderId);
        return ordersDetails.stream().map(ordersDetail ->
                new OrderDetail.Detail(
                    ordersDetail.getId() ,
                    ordersDetail.getProduct().getId(),
                    ordersDetail.getProductName(),
                    ordersDetail.getLength(),
                    ordersDetail.getWidth(),
                    ordersDetail.getQty(),
                    ordersDetail.isCustomYN(),
                    ordersDetail.getCustomPrice(),
                    ordersDetail.getBasePrice(),
                    ordersDetail.getAmt(),
                    ordersDetail.getTax()
                )
        ).toList();
    }

    public void saveAll(List<OrdersDetail> orderDetails) {
        orderDetailRepository.saveAll(orderDetails);
    }
}
