package com.personal.domain.store.service;

import com.personal.common.entity.AuthUser;
import com.personal.domain.store.dto.StoreRequest;
import com.personal.domain.store.dto.StoreResponse;
import com.personal.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;

    public Page<StoreResponse.Info> getStores(AuthUser authUser , StoreRequest.GetStores getStores) {
        Pageable pageable = PageRequest.of(getStores.page() - 1, getStores.size());
        return storeRepository.getStores(getStores, pageable);
    }
}
