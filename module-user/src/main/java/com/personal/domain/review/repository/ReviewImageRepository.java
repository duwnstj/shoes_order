package com.personal.domain.review.repository;

import com.personal.entity.review.Review;
import com.personal.entity.review.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
    List<ReviewImage> findByReview(Review review);
}
