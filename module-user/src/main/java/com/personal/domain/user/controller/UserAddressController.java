package com.personal.domain.user.controller;

import com.personal.common.entity.AuthUser;
import com.personal.common.entity.SuccessResponse;
import com.personal.domain.user.dto.UserAddressRequest;
import com.personal.domain.user.service.UserAddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/users/address")
@RestController
public class UserAddressController {

    private final UserAddressService userAddressService;

    @PostMapping
    public ResponseEntity<SuccessResponse<Void>> addUserAddress(
            @AuthenticationPrincipal AuthUser authUser ,
            @Valid @RequestBody UserAddressRequest.UserAddress userAddress
            ) {
        userAddressService.addUserAddress(authUser , userAddress);
        return ResponseEntity.ok()
                .body(SuccessResponse.of(null));
    }

    @PatchMapping("/{userAddressId}")
    public ResponseEntity<SuccessResponse<Void>> modUserAddress(
            @AuthenticationPrincipal AuthUser authUser ,
            @PathVariable Long userAddressId,
            @Valid @RequestBody UserAddressRequest.UserAddress userAddress
    ) {
        userAddressService.modUserAddress(authUser , userAddressId , userAddress);
        return null;
    }
}
