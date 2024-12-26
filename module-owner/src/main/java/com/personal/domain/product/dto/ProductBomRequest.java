package com.personal.domain.product.dto;

public sealed interface ProductBomRequest permits
        ProductBomRequest.CreateBom {
    record CreateBom(
                Long baseProductId,
                String baseProductName,
                Long baseProductQty,
                Long materialProductId,
                String materialProductName,
                Long materialProductQty

    )
            implements ProductBomRequest {
    }
}

