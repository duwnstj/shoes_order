package com.personal.domain.store.dto;

import com.personal.domain.product.dto.ProductResponse;

import java.util.List;

public sealed interface StoreResponse permits
        StoreResponse.Info
{
    record Info(
            Long id,
            String name ,
            String tel ,
            String zip ,
            String address ,
            String addressDetail ,
            String description ,
            Long productCnt
    ) implements StoreResponse {
    }
}
