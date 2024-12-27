package com.personal.domain.stock.dto;

import com.personal.entity.product.ProductType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public sealed interface StockResponse permits
        StockResponse.Infos {
    record Infos(

            ProductType type,
            Long size,
            Long qty,
            Long price,
            String lot,
            String description,

            LocalDateTime createAt,
            LocalDateTime updatedAt
    )
            implements StockResponse {
        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        public String getFormattedCreateAt() {
            return createAt.format(FORMATTER);
        }

        public String getFormattedUpdatedAt() {
            return updatedAt.format(FORMATTER);
        }

    }
}
