package com.personal.domain.product.service;

import com.personal.domain.product.dto.ProductRequest;
import com.personal.domain.product.dto.ProductResponse;
import com.personal.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse.Info> getProducts(Long storeId , ProductRequest.GetProduct getProduct) {
        return productRepository.getProducts(storeId, getProduct);
    }
}
