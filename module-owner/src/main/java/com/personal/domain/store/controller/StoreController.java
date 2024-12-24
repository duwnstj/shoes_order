package com.personal.domain.store.controller;

import com.personal.common.entity.AuthUser;
import com.personal.common.entity.SuccessResponse;
import com.personal.domain.store.dto.StoreRequest;
import com.personal.domain.store.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/v1/stores")
public class StoreController {
    private final StoreService storeService;

    /**
     * 매장 등록
     */
    @PostMapping
    public ResponseEntity<SuccessResponse<Void>> saveStores(
            @Valid @RequestBody StoreRequest.SaveStores saveStores,
            @AuthenticationPrincipal AuthUser authUser
    ) {
        storeService.saveStores(saveStores, authUser);
        return ResponseEntity.ok()
                .body(SuccessResponse.of(null));
    }
}
