package com.personal.domain.product.service;

import com.personal.common.code.ResponseCode;
import com.personal.common.entity.AuthUser;
import com.personal.common.enums.UserRole;
import com.personal.domain.owner.service.OwnerCommonService;
import com.personal.domain.product.dto.ProductRequest;
import com.personal.domain.product.dto.ProductResponse;
import com.personal.domain.product.exception.ForbiddenProductsException;
import com.personal.domain.product.repository.ProductRepository;
import com.personal.domain.store.service.StoreCommonService;
import com.personal.entity.product.Product;
import com.personal.entity.store.Store;
import com.personal.entity.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;
    private final StoreCommonService storeCommonService;
    private final OwnerCommonService ownerCommonService;

    public Page<ProductResponse.Infos> getProducts(ProductRequest.GetProducts getProducts) {
        Pageable pageable = PageRequest.of(getProducts.page() - 1, getProducts.size());
        return productRepository.getProducts(getProducts, pageable);
    }

    @Transactional
    public void addProduct(ProductRequest.AddProduct addProduct, Long storeId, AuthUser authUser) {
        User owner = ownerCommonService.getUserById(authUser.getUserId());
        if (owner.getRole().equals(UserRole.ROLE_USER)) {
            throw new ForbiddenProductsException(ResponseCode.FORBIDDEN_PRODUCTS_ADD);
        }
        Store store = storeCommonService.getStores(storeId);
        Product product = Product.builder()
                .store(store)
                .type(addProduct.type())
                .name(addProduct.name())
                .category(addProduct.category())
                .material(addProduct.material())
                .spacing(addProduct.spacing())
                .basePrice(addProduct.basePrice())
                .customPrice(addProduct.customPrice())
                .description(addProduct.description())
                .build();
        productRepository.save(product);
    }
}
