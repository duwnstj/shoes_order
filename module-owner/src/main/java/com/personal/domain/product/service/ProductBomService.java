package com.personal.domain.product.service;

import com.personal.common.entity.AuthUser;
import com.personal.domain.product.dto.ProductBomRequest;
import com.personal.domain.product.repository.ProductBomRepository;
import com.personal.entity.product.Product;
import com.personal.entity.product.ProductBom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductBomService {
    private final ProductBomRepository productBomRepository;
    private final ProductCommonService productCommonService;


    @Transactional
    public void createBom(ProductBomRequest.CreateBom createBom, AuthUser authUser) {


        //완제품 객체
        Product product = productCommonService.getProducts(createBom.baseProductId());

        //원자재
        Product material = productCommonService.getProducts(createBom.materialProductId());
        ProductBom productBom = ProductBom.builder()
                .baseProduct(product)
                .baseQty(createBom.baseProductQty())
                .materialProduct(material)
                .materialQty(createBom.materialProductQty())
                .build();
        productBomRepository.save(productBom);

    }
}
