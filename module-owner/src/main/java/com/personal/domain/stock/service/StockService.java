package com.personal.domain.stock.service;

import com.personal.common.code.ResponseCode;
import com.personal.domain.stock.exception.InvalidStockException;
import com.personal.domain.stock.exception.NotFoundStockException;
import com.personal.domain.stock.repository.StockRepository;
import com.personal.entity.stock.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StockService {
    private final StockRepository stockRepository;

    @Transactional
    public void increaseStock(Long productId, Long baseQty) {
        Stock stock = findStockProduct(productId);

        if (baseQty == null || baseQty <= 0) {
            throw new InvalidStockException(ResponseCode.INVALID_STOCK_QUNTITY);
        }
        stock.increase(baseQty);
        stockRepository.save(stock);

    }

    @Transactional
    public void decreaseStock(Long productId, Long materialQty) {
        Stock stock = findStockProduct(productId);

        if (materialQty == null || materialQty <= 0) {
            throw new InvalidStockException(ResponseCode.INVALID_STOCK_QUNTITY);
        }

        if (stock.getQty() < materialQty) {
            throw new InvalidStockException(ResponseCode.INVALID_STOCK);
        }
        stock.decrease(materialQty);
        stockRepository.save(stock);
    }

    public Stock findStockProduct(Long productId) {
        return stockRepository.findByProductId(productId)
                .orElseThrow(() -> new NotFoundStockException(ResponseCode.NOT_FOUND_STOCK));
    }
}
