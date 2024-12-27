package com.personal.domain.stock.service;

import com.personal.common.entity.AuthUser;
import com.personal.domain.stock.dto.StockRequest;
import com.personal.domain.stock.dto.StockResponse;
import com.personal.domain.stock.repository.StockRepository;
import com.personal.domain.store.service.StoreCommonService;
import com.personal.entity.store.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StockService {
    private final StockRepository stockRepository;
    private final StoreCommonService storeCommonService;

    public Page<StockResponse.Infos> getStocks(Long storeId, StockRequest.GetStocks getStocks, AuthUser authUser) {
        Pageable pageable = PageRequest.of(getStocks.page() - 1, getStocks.size());

        Store store = storeCommonService.getStores(storeId);
        storeCommonService.validateUserAccess(authUser, store);

        return stockRepository.getStocks(pageable, storeId, getStocks);

    }


}
