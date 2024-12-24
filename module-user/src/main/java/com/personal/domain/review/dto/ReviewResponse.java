package com.personal.domain.review.dto;

import java.time.LocalDateTime;
import java.util.List;

public sealed interface ReviewResponse permits
        ReviewResponse.Info ,
        ReviewResponse.ImageInfo
{
    record Info(
            String title,
            String contents,
            Double star,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            List<ImageInfo> imageInfos
    ) implements ReviewResponse {
    }

    record ImageInfo(
            Long id,
            String url
    ) implements ReviewResponse {
    }
}
