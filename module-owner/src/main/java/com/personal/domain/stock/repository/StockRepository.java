package com.personal.domain.stock.repository;


import com.personal.entity.stock.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

//    @Query("select st from Stock st where st.product.id=:productId")
//    Optional<Stock> findByProductId(@Param("productId") Long productId);

    @Query("select st from Stock st where st.store.id=:storeId order by st.updatedAt desc ")
    Page<Stock> findAllByStores(@Param("storeId") Long storeId, Pageable pageable);
}
