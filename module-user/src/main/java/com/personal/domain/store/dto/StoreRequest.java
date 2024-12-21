package com.personal.domain.store.dto;

import java.util.Objects;

public sealed interface StoreRequest permits
        StoreRequest.GetStores
{
    record GetStores(
        String type,
        String value,
        String sort,
        Integer page ,
        Integer size
    ) implements StoreRequest {
        public GetStores {
            if (Objects.isNull(page)) page = 1;
            if (Objects.isNull(size)) size = 10;
        }
    }
}
