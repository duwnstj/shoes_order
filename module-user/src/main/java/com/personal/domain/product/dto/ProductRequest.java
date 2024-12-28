package com.personal.domain.product.dto;

public sealed interface ProductRequest permits
        ProductRequest.GetProduct
{
    record GetProduct(
            String type,    // 상품명 , 카테고리 type
            String value,
            String sort,
            Long minPrice,
            Long maxPrice
    ) implements ProductRequest {
    }
}
