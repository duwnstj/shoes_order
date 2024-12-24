package com.personal.domain.store.repository;

import com.personal.entity.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> , StoreDslRepository {
}
