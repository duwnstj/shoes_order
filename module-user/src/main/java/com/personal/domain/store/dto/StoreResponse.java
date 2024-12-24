package com.personal.domain.store.dto;

import com.personal.domain.product.dto.ProductResponse;

import java.util.List;

public sealed interface StoreResponse permits
        StoreResponse.Infos ,
        StoreResponse.Info
{
    record Infos(
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

    record Info(
            Long id,
            String name ,
            String tel ,
            String zip ,
            String address ,
            String addressDetail ,
            String description ,
            List<ProductResponse.Info> productList
    ) implements StoreResponse {
    }
}
