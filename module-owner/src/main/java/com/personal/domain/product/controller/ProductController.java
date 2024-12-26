package com.personal.domain.product.controller;

import com.personal.common.entity.AuthUser;
import com.personal.common.entity.SuccessResponse;
import com.personal.domain.product.dto.ProductRequest;
import com.personal.domain.product.dto.ProductResponse;
import com.personal.domain.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/stores")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    /**
     * 상품 다건 조회
     *
     * @param getProducts
     */
    @GetMapping("/{storeId}/products")
    public ResponseEntity<SuccessResponse<Page<ProductResponse.Infos>>> getProducts(
            @ModelAttribute ProductRequest.GetProducts getProducts
    ) {
        return ResponseEntity.ok()
                .body(SuccessResponse.of(productService.getProducts(getProducts)));

    }

    /**
     * 상품 등록
     */
    @PostMapping("/{storeId}/products")
    public ResponseEntity<SuccessResponse<Void>> addProduct(
            @Valid @RequestBody ProductRequest.AddProduct addProduct,
            @PathVariable Long storeId,
            @AuthenticationPrincipal AuthUser authUser
    ) {
        productService.addProduct(addProduct, storeId, authUser);
        return ResponseEntity.ok()
                .body(SuccessResponse.of(null));
    }
}
