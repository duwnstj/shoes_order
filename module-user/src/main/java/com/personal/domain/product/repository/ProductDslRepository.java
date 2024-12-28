package com.personal.domain.product.repository;

import com.personal.domain.product.dto.ProductRequest;
import com.personal.domain.product.dto.ProductResponse;

import java.util.List;

public interface ProductDslRepository {
    List<ProductResponse.Info> getProducts(Long storeId , ProductRequest.GetProduct getProduct);
}
