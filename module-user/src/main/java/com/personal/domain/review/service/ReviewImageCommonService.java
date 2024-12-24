package com.personal.domain.review.service;

import com.personal.domain.review.repository.ReviewImageRepository;
import com.personal.entity.review.Review;
import com.personal.entity.review.ReviewImage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewImageCommonService {
    private final ReviewImageRepository reviewImageRepository;

    public List<ReviewImage> getInfos(Review review) {
        return reviewImageRepository.findByReview(review);
    }
}
