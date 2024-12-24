package com.personal.domain.ship.repository;

import com.personal.domain.ship.dto.ShippingRequest;
import com.personal.domain.ship.dto.ShippingResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShippingDslRepository {
    Page<ShippingResponse.Info> getShippingStatus(Long userId , ShippingRequest.GetShipping getShipping , Pageable pageable);
}
