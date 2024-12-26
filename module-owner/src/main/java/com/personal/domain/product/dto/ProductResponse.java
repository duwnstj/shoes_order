package com.personal.domain.product.dto;

import com.personal.entity.product.ProductType;

public sealed interface ProductResponse permits
        ProductResponse.Infos {
    record Infos(
            Long id,
            ProductType type,
            String name,
            String category,
            String material,
            Long basePrice,
            Long customPrice


    ) implements ProductResponse{

    }
}
