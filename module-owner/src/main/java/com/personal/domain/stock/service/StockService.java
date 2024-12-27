package com.personal.domain.stock.service;

import com.personal.common.entity.AuthUser;
import com.personal.common.entity.SuccessResponse;
import com.personal.domain.stock.dto.StockResponse;
import com.personal.domain.stock.repository.StockRepository;
import com.personal.domain.store.service.StoreCommonService;
import com.personal.entity.stock.Stock;
import com.personal.entity.store.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StockService {
    private final StockRepository stockRepository;
    private final StoreCommonService storeCommonService;

    public ResponseEntity<SuccessResponse<Page<StockResponse.Infos>>> getStocks(Long storeId, Integer page, Integer size, AuthUser authUser) {
        Pageable pageable = PageRequest.of(page - 1, size);

        Store store = storeCommonService.getStores(storeId);
        storeCommonService.validateUserAccess(authUser, store);

        Page<Stock> stockList = stockRepository.findAllByStores(storeId, pageable);

        Page<StockResponse.Infos> stockResponsePage = stockList
                .map(stock -> new StockResponse.Infos(
                        stock.getType(),
                        stock.getSize(),
                        stock.getQty(),
                        stock.getPrice(),
                        stock.getLot(),
                        stock.getDescription(),
                        stock.getCreatedAt(),
                        stock.getUpdatedAt()
                ));
        return ResponseEntity.ok(new SuccessResponse<>(stockResponsePage));

    }


}
