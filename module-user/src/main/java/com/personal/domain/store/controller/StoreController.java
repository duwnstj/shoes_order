package com.personal.domain.store.controller;

import com.personal.common.entity.AuthUser;
import com.personal.common.entity.SuccessResponse;
import com.personal.domain.store.dto.StoreRequest;
import com.personal.domain.store.dto.StoreResponse;
import com.personal.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
@RestController
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    public ResponseEntity<SuccessResponse<Page<StoreResponse.Infos>>> getStores(
            @ModelAttribute StoreRequest.GetStores getStores
            ) {
        return ResponseEntity.ok().body(SuccessResponse.of(storeService.getStores(getStores)));
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<SuccessResponse<StoreResponse.Info>> getStore(
            @PathVariable Long storeId
            ) {
        return ResponseEntity.ok().body(SuccessResponse.of(storeService.getStore(storeId)));
    }
}
