package com.personal.domain.product.service;

import com.personal.common.code.ResponseCode;
import com.personal.common.entity.AuthUser;
import com.personal.domain.product.dto.ProductBomRequest;
import com.personal.domain.product.dto.ProductBomResponse;
import com.personal.domain.product.repository.ProductBomRepository;
import com.personal.domain.stock.service.StockService;
import com.personal.domain.store.exception.StoreOwnerMismatchException;
import com.personal.domain.store.service.StoreCommonService;
import com.personal.entity.product.Product;
import com.personal.entity.product.ProductBom;
import com.personal.entity.store.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductBomService {
    private final ProductBomRepository productBomRepository;
    private final ProductCommonService productCommonService;
    private final StoreCommonService storeCommonService;
    private final ProductBomCommonService productBomCommonService;
    private final StockService stockService;


    @Transactional
    public void createBom(ProductBomRequest.CreateBom createBom, AuthUser authUser, Long storeId, Long productId) {
        Store store = storeCommonService.getStores(storeId);
        if (!authUser.getUserId().equals(store.getUser().getId())) {
            throw new StoreOwnerMismatchException(ResponseCode.FORBIDDEN_PRODUCTS_ADD);
        }
        productCommonService.getProducts(productId);

        //완제품 객체
        Product baseProduct = productCommonService.getProducts(createBom.baseProductId());

        //원자재
        Product materialProduct = productCommonService.getProducts(createBom.materialProductId());
        ProductBom productBom = ProductBom.builder()
                .baseProduct(baseProduct)
                .baseQty(createBom.baseProductQty())
                .materialProduct(materialProduct)
                .materialQty(createBom.materialProductQty())
                .build();
        productBomRepository.save(productBom);

        //재고 차감 로직
        adjustStockForBom(baseProduct, materialProduct, createBom.baseProductQty(), createBom.materialProductQty());

    }

    private void adjustStockForBom(Product baseProduct, Product materialProduct, Long baseQty, Long materialQty) {
        // 원자재 재고 차감
        stockService.decreaseStock(materialProduct.getId(), materialQty);

        // 완제품 재고 추가
        stockService.increaseStock(baseProduct.getId(), baseQty);
    }


    @Transactional
    public void updateBom(ProductBomRequest.UpdateBom updateBom, Long storeId, Long productId, Long bomId, AuthUser authUser) {
        Store store = storeCommonService.getStores(storeId);
        if (!authUser.getUserId().equals(store.getUser().getId())) {
            throw new StoreOwnerMismatchException(ResponseCode.FORBIDDEN_PRODUCTS_UPDATE);
        }
        productCommonService.getProducts(productId);
        //완제품 객체
        Product baseProduct = productCommonService.getProducts(updateBom.baseProductId());

        //원자재
        Product materialProduct = productCommonService.getProducts(updateBom.materialProductId());

        ProductBom productBom = productBomCommonService.getBoms(bomId);

        Long previusBaseQty = productBom.getBaseQty();
        Long previusMaterialQty = productBom.getMaterialQty();
        productBom.updateBom(
                baseProduct,
                updateBom.baseProductQty(),
                materialProduct,
                updateBom.materialProductQty()
        );
        adjustStockForUpdate(productBom.getBaseProduct(), productBom.getMaterialProduct(),
                previusBaseQty, productBom.getBaseQty(),
                previusMaterialQty, productBom.getMaterialQty());
    }

    public void adjustStockForUpdate(Product baseProduct, Product materialProduct,
                                     Long previusBaseQty, Long newBaseQty,
                                     Long previusMaterialQty, Long newMaterialQty) {

        //원자재 재고 조정
        stockService.decreaseStock(materialProduct.getId(), newMaterialQty - previusMaterialQty);
        //완제품 재고 조정
        stockService.increaseStock(baseProduct.getId(), newBaseQty - previusBaseQty);

    }

    public List<ProductBomResponse.GetInfos> getBoms(Long storeId, Long productId) {
        storeCommonService.getStores(storeId);
        productCommonService.getProducts(productId);

        List<ProductBom> productBomList = productBomRepository.findAllByIdAndMaterial(productId);
        return productBomList.stream()
                .map(productBom -> new ProductBomResponse.GetInfos(
                        productBom.getBaseProduct().getId(),
                        productBom.getBaseProduct().getName(),
                        productBom.getBaseQty(),
                        productBom.getMaterialProduct().getId(),
                        productBom.getMaterialProduct().getName(),
                        productBom.getMaterialQty()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteBom(Long storeId, Long productId, Long bomId, AuthUser authUser) {
        Store store = storeCommonService.getStores(storeId);
        if (!authUser.getUserId().equals(store.getUser().getId())) {
            throw new StoreOwnerMismatchException(ResponseCode.FORBIDDEN_PRODUCTS_DELETE);
        }
        productCommonService.getProducts(productId);

        //Bom 조회
        ProductBom productBom = productBomCommonService.getBoms(bomId);

        //재고 복구
        stockService.increaseStock(productBom.getMaterialProduct().getId(), productBom.getMaterialQty());
        stockService.decreaseStock(productBom.getBaseProduct().getId(), productBom.getBaseQty());


        //soft delete
        //BOM(생산계획) 삭제
        productBomRepository.deleteById(bomId);


    }
}
