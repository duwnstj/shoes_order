package com.personal.domain.review.repository;

import com.personal.domain.review.dto.ReviewRequest;
import com.personal.domain.review.dto.ReviewResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewDslRepository {
    Page<ReviewResponse.Info> getMyReviews(Long userId , ReviewRequest.MyReview myReview , Pageable pageable);
    Page<ReviewResponse.Info> getMyReviewsByStore(Long userId , Long storeId , ReviewRequest.MyReview myReview , Pageable pageable);
}
