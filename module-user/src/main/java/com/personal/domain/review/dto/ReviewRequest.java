package com.personal.domain.review.dto;

import java.util.List;

public sealed interface ReviewRequest permits
        ReviewRequest.AddReview,
        ReviewRequest.ModReview
{
    record AddReview(
            String title,
            String contents,
            Double star
    ) implements ReviewRequest {
    }

    record ModReview(
            String title,
            String contents,
            Double star,
            List<Long> modImageNumber
    ) implements ReviewRequest {
    }
}
