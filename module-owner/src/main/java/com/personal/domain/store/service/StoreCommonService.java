package com.personal.domain.store.service;

import com.personal.common.code.ResponseCode;
import com.personal.common.entity.AuthUser;
import com.personal.common.exception.custom.NotFoundException;
import com.personal.domain.store.exception.StoreOwnerMismatchException;
import com.personal.domain.store.repository.StoreRepository;
import com.personal.entity.history.InputHistory;
import com.personal.entity.store.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StoreCommonService {
    private final StoreRepository storeRepository;


    public Store getStores(Long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new NotFoundException(ResponseCode.NOT_FOUND_STORE));
    }

    public void validateUserAccess(AuthUser authUser, Long storeId) {
        Store store = getStores(storeId);
        if (!authUser.getUserId().equals(store.getUser().getId())) {
            throw new StoreOwnerMismatchException(ResponseCode.FORBIDDEN_STORES_USER);
        }
    }



}
