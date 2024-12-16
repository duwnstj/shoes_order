package com.personal.domain.owner.controller;

import com.personal.common.entity.SuccessResponse;
import com.personal.domain.owner.dto.OwnerRequest;
import com.personal.domain.owner.service.OwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequestMapping("api/v1/owners")
@RestController
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerService ownerService;

    @PostMapping("/login")
    public ResponseEntity<SuccessResponse<Void>> login(
            @Valid @RequestBody OwnerRequest.Login login
    ) {
        String token = ownerService.login(login);
        return ResponseEntity.ok()
                .header(AUTHORIZATION, token)
                .body(SuccessResponse.of(null));
    }

    @PostMapping("/register")
    public ResponseEntity<SuccessResponse<Void>> login(
            @Valid @RequestBody OwnerRequest.Register register){
        ownerService.register(register);
        return ResponseEntity.ok()
                .body(SuccessResponse.of(null));
    }
}
