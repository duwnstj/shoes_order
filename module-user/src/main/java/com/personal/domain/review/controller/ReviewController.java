package com.personal.domain.review.controller;

import com.personal.common.entity.AuthUser;
import com.personal.common.entity.SuccessResponse;
import com.personal.domain.review.dto.ReviewRequest;
import com.personal.domain.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class ReviewController {
    private final ReviewService reviewService;

    /**
     * 리뷰 등록
     * */
    @PostMapping("/orders/{orderId}/review")
    public ResponseEntity<SuccessResponse<Void>> addReview(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long orderId,
            @Valid @ModelAttribute ReviewRequest.AddReview addReview,
            @RequestParam(name = "images" , required = false) List<MultipartFile> files
    ) {
        reviewService.addReview(authUser , orderId , addReview , files);
        return ResponseEntity.ok().body(SuccessResponse.of(null));
    }

    /**
     * 리뷰 수정
     * */
    @PatchMapping("/orders/{orderId}/review")
    public ResponseEntity<SuccessResponse<Void>> modReview(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long orderId,
            @Valid @ModelAttribute ReviewRequest.ModReview modReview,
            @RequestParam(name = "images" , required = false) List<MultipartFile> files
    ) {
        reviewService.modReview(authUser, orderId, modReview, files);
        return ResponseEntity.ok().body(SuccessResponse.of(null));
    }

    /**
     * 리뷰 삭제
     * */
    @DeleteMapping("/orders/{orderId}/review")
    public ResponseEntity<SuccessResponse<Void>> removeReview(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long orderId
    ) {
        reviewService.removeReview(authUser , orderId);
        return ResponseEntity.ok().body(SuccessResponse.of(null));
    }
}
