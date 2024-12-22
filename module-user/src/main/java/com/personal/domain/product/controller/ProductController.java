package com.personal.domain.product.controller;

import com.personal.common.entity.SuccessResponse;
import com.personal.domain.product.dto.ProductRequest;
import com.personal.domain.product.dto.ProductResponse;
import com.personal.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores/{storeId}/products")
@RestController
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<ProductResponse.Info>>> getProducts(
            @PathVariable Long storeId ,
            @ModelAttribute ProductRequest.GetProduct getProduct
            ) {
        return ResponseEntity.ok().body(SuccessResponse.of(productService.getProducts(storeId , getProduct)));
    }
}
