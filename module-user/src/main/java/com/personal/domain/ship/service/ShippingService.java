package com.personal.domain.ship.service;

import com.personal.common.entity.AuthUser;
import com.personal.domain.ship.dto.ShippingRequest;
import com.personal.domain.ship.dto.ShippingResponse;
import com.personal.domain.ship.repository.ShippingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShippingService {
    private final ShippingRepository shippingRepository;

    public Page<ShippingResponse.Info> getShippingStatus(AuthUser authUser, ShippingRequest.GetShipping getShipping) {
        Pageable pageable = PageRequest.of(getShipping.page() - 1, getShipping.size());
        return shippingRepository.getShippingStatus(authUser.getUserId(), getShipping , pageable);
    }
}
