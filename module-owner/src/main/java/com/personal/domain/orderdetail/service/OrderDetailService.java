package com.personal.domain.orderdetail.service;

import com.personal.common.entity.AuthUser;
import com.personal.domain.orderdetail.dto.OrderDetailRequest;
import com.personal.domain.orderdetail.dto.OrderDetailResponse;
import com.personal.domain.orderdetail.repository.OrderDetailRepository;
import com.personal.domain.store.service.StoreCommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final StoreCommonService storeCommonService;


    public Page<OrderDetailResponse.GetInfos> getOrders(Long storeId, AuthUser authUser, OrderDetailRequest.GetOrders getOrders) {
        Pageable pageable = PageRequest.of(getOrders.page() - 1, getOrders.size());
        storeCommonService.getStores(storeId);
        storeCommonService.validateUserAccess(authUser, storeId);

        return orderDetailRepository.getOrders(pageable, storeId, getOrders);
    }


}
