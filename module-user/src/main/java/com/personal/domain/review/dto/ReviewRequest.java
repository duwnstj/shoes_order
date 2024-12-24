package com.personal.domain.review.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public sealed interface ReviewRequest permits
        ReviewRequest.AddReview,
        ReviewRequest.ModReview,
        ReviewRequest.MyReview
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

    record MyReview(
            Long star ,
            LocalDate startDate,
            LocalDate endDate,
            Integer page,
            Integer size
    ) implements ReviewRequest {
        public MyReview {
            if (Objects.isNull(page)) page = 1;
            if (Objects.isNull(size)) size = 10;
        }
    }
}
