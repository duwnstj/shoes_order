package com.personal.domain.store.service;

import com.personal.common.code.ResponseCode;
import com.personal.common.exception.custom.NotFoundException;
import com.personal.domain.store.repository.StoreRepository;
import com.personal.entity.store.Store;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class StoreCommonService {
    private final StoreRepository storeRepository;

    public Store getStoreById(Long id) {
        return storeRepository.findById(id).orElseThrow(() -> new NotFoundException(ResponseCode.NOT_FOUND_STORE));
    }
}
