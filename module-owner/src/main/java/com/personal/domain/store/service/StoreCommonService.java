package com.personal.domain.store.service;

import com.personal.common.code.ResponseCode;
import com.personal.common.exception.custom.NotFoundException;
import com.personal.domain.store.repository.StoreRepository;
import com.personal.entity.store.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StoreCommonService {
    private final StoreRepository storeRepository;


    public Store getStores(Long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new NotFoundException(ResponseCode.NOT_FOUND_STORE));
    }
}
