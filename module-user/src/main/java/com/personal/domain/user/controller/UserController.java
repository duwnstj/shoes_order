package com.personal.domain.user.controller;

import com.personal.common.entity.AuthUser;
import com.personal.common.entity.SuccessResponse;
import com.personal.domain.user.dto.UserRequest;
import com.personal.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<SuccessResponse<Void>> login(
            @Valid @RequestBody UserRequest.Login login
    ) {
        String[] token = userService.login(login);
        return ResponseEntity.ok()
                .header(AUTHORIZATION, token[0])
                .header("REFRESH_TOKEN", token[1])
                .body(SuccessResponse.of(null));
    }

    @PostMapping("/register")
    public ResponseEntity<SuccessResponse<Void>> register(
            @Valid @RequestBody UserRequest.Register register
    ) {
        String[] token = userService.register(register);
        return ResponseEntity.ok()
                .header(AUTHORIZATION, token[0])
                .header("REFRESH_TOKEN", token[1])
                .body(SuccessResponse.of(null));
    }

    @PostMapping("/logout")
    public ResponseEntity<SuccessResponse<Void>> logout(
            @AuthenticationPrincipal AuthUser user
    ) {
        userService.logout(user);
        return ResponseEntity.ok()
                .body(SuccessResponse.of(null));
    }
}
