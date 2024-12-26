package com.personal.domain.product.dto;

public sealed interface ProductBomResponse permits
        ProductBomResponse.GetInfos {
    record GetInfos(
            Long baseProductId,
            String baseProductName,
            Long baseProductQty,
            Long materialProductId,
            String materialProductName,
            Long materialProductQty
    )
            implements ProductBomResponse {
    }
}

