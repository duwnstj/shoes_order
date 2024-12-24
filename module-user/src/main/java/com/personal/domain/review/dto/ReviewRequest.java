package com.personal.domain.review.dto;

public sealed interface ReviewRequest permits
        ReviewRequest.WriteReview
{
    record WriteReview(
            String title,
            String contents,
            Double star
    ) implements ReviewRequest {
    }
}
