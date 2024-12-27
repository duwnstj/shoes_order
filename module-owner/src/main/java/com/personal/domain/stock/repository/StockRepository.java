package com.personal.domain.stock.repository;


import com.personal.entity.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long>, StockDslRepository {


}
