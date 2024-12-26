package com.personal.domain.product.repository;


import com.personal.domain.product.dto.ProductRequest;
import com.personal.domain.product.dto.ProductResponse;
import com.personal.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>,ProductDslRepository {
    Page<ProductResponse.Infos> getProducts(ProductRequest.GetProducts getProducts, Pageable pageable);
}
