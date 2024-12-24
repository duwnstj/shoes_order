package com.personal.domain.store.service;

import com.personal.common.entity.AuthUser;
import com.personal.domain.product.dto.ProductResponse;
import com.personal.domain.product.service.ProductCommonService;
import com.personal.domain.store.dto.StoreRequest;
import com.personal.domain.store.dto.StoreResponse;
import com.personal.domain.store.repository.StoreRepository;
import com.personal.entity.product.Product;
import com.personal.entity.store.Store;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreCommonService storeCommonService;
    private final ProductCommonService productCommonService;
    private final StoreRepository storeRepository;

    public Page<StoreResponse.Infos> getStores(StoreRequest.GetStores getStores) {
        Pageable pageable = PageRequest.of(getStores.page() - 1, getStores.size());
        return storeRepository.getStores(getStores, pageable);
    }

    public StoreResponse.Info getStore(Long storeId) {
        Store store = storeCommonService.getStoreById(storeId);
        List<Product> products = productCommonService.getProductsById(storeId);
        List<ProductResponse.Info> productInfos = products
                .stream()
                .map(product -> new ProductResponse.Info(product.getId() , product.getName() , product.getCategory() , product.getBasePrice() , product.getCustomPrice()))
                .toList();
        return new StoreResponse.Info(store.getId() , store.getName() , store.getTel() , store.getZip() , store.getAddress() , store.getAddressDetail() , store.getDescription() , productInfos);
    }
}
