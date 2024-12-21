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
    public ResponseEntity<SuccessResponse<Page<StoreResponse.Info>>> getStores(
            @AuthenticationPrincipal AuthUser authUser ,
            @ModelAttribute StoreRequest.GetStores getStores
            ) {
        log.info("getStores {}", getStores);
        return ResponseEntity.ok().body(SuccessResponse.of(storeService.getStores(authUser, getStores)));
    }
}
