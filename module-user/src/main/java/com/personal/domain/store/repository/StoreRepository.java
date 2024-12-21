package com.personal.domain.store.repository;

import com.personal.entity.store.Store;
import org.springframework.data.repository.CrudRepository;

public interface StoreRepository extends CrudRepository<Store, Integer> , StoreDslRepository {
}
