package com.personal.domain.store.repository;

import com.personal.domain.store.dto.StoreRequest;
import com.personal.domain.store.dto.StoreResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoreDslRepository {
    Page<StoreResponse.Info> getStores(StoreRequest.GetStores getStores , Pageable pageable);
}
