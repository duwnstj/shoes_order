package com.personal.domain.product.controller;

import com.personal.common.entity.AuthUser;
import com.personal.common.entity.SuccessResponse;
import com.personal.domain.product.dto.ProductBomRequest;
import com.personal.domain.product.service.ProductBomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("api/v1/stores")
@RestController
public class ProductBomController {
    private final ProductBomService productBomService;


    /**
     * 상품 Bom 생성
     * @param createBom
     * @param authUser
     * @return
     */
    @PostMapping("/products//bom")
    public ResponseEntity<SuccessResponse<Void>> createBom(
            @RequestBody ProductBomRequest.CreateBom createBom,
            @AuthenticationPrincipal AuthUser authUser
    ) {
        productBomService.createBom(createBom, authUser);

        return ResponseEntity.ok()
                .body(SuccessResponse.of(null));
    }
}
