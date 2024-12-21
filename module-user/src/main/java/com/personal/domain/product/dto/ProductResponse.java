package com.personal.domain.product.dto;

public sealed interface ProductResponse permits
    ProductResponse.Info
{
    record Info(
        Long id ,
        String name ,
        String category ,
        Long basePrice ,
        Long customPrice
    ) implements ProductResponse {
    }
}
