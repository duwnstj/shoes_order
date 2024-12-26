package com.personal.domain.product.repository;


import com.personal.domain.product.dto.ProductRequest;
import com.personal.domain.product.dto.ProductResponse;
import com.personal.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductDslRepository {
    Page<ProductResponse.Infos> getProducts(ProductRequest.GetProducts getProducts, Pageable pageable);


    @Query("select p from Product p where p.store.id=:storeId and p.id =:productId")
    Product getProduct(@Param("storeId") Long storeId, @Param("productId") Long productId);

}
