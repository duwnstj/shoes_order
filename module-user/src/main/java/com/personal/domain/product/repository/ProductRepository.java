package com.personal.domain.product.repository;

import com.personal.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByStore_Id(Long storeId);
}
