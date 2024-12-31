package com.personal.domain.product.repository;


import com.personal.entity.product.ProductBom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductBomRepository extends JpaRepository<ProductBom, Long> {

    @Query("SELECT  pb FROM ProductBom pb " +
            "JOIN FETCH pb.baseProduct bp " +
            "JOIN FETCH pb.materialProduct mp " +
            "WHERE bp.id = :productId ")
    List<ProductBom> findAllByIdAndMaterial(@Param("productId") Long productId);
}
