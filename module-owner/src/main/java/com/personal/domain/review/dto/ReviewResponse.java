package com.personal.domain.review.dto;

import java.time.LocalDate;
import java.util.List;

public sealed interface ReviewResponse permits
        ReviewResponse.GetInfos,
        ReviewResponse.ImageInfo {
    record GetInfos(
            String userName,
            Double star,
            String title,
            List<ImageInfo> imageInfos,
            String content,
            LocalDate startDate,
            LocalDate endDate


    )
            implements ReviewResponse {
    }

    public record ImageInfo(
            Long id,
            String url

    ) implements ReviewResponse {
    }
}
