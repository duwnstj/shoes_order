package com.personal.domain.review.dto;

import java.time.LocalDate;

public sealed interface ReviewRequest permits
        ReviewRequest.GetReviews {
    record GetReviews(
            Integer page,
            Integer size,
            String sort,
            Double star,
            LocalDate startDate,
            LocalDate endDate
    )
            implements ReviewRequest {
    }
}

