package com.personal.domain.review.repository;

import com.personal.domain.review.dto.ReviewRequest;
import com.personal.domain.review.dto.ReviewResponse;
import com.personal.entity.review.Review;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.personal.entity.review.QReview.review;
import static com.personal.entity.review.QReviewImage.reviewImage;

@Slf4j
@RequiredArgsConstructor
public class ReviewDslRepositoryImpl implements ReviewDslRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ReviewResponse.Info> getMyReviews(Long userId, ReviewRequest.MyReview myReview, Pageable pageable) {
        return getReviews(userId, null, myReview, pageable);
    }

    @Override
    public Page<ReviewResponse.Info> getMyReviewsByStore(Long userId, Long storeId, ReviewRequest.MyReview myReview, Pageable pageable) {
        return getReviews(userId, storeId, myReview, pageable);
    }

    private Page<ReviewResponse.Info> getReviews(Long userId, Long storeId, ReviewRequest.MyReview myReview, Pageable pageable) {
        List<Review> reviews = fetchReviews(userId, storeId, myReview, pageable);

        // 리뷰 ID 추출
        List<Long> reviewIds = reviews.stream().map(Review::getId).toList();
        if (reviewIds.isEmpty()) {
            return new PageImpl<>(Collections.emptyList(), pageable, 0);
        }
        
        Map<Long, List<ReviewResponse.ImageInfo>> reviewImagesMap = fetchReviewImages(reviewIds);
        
        List<ReviewResponse.Info> responseList = mapReviewsToResponse(reviews, reviewImagesMap);
        
        Long totalCount = fetchTotalCount(userId, storeId, myReview);
        
        return new PageImpl<>(responseList, pageable, totalCount);
    }

    /**
     * Review만 조회해서 데이터 가져오기 
     * */
    private List<Review> fetchReviews(Long userId, Long storeId, ReviewRequest.MyReview myReview, Pageable pageable) {
        return queryFactory
                .selectFrom(review)
                .where(
                        review.orders.user.id.eq(userId),
                        storeId != null ? review.orders.store.id.eq(storeId) : null,
                        starCondition(myReview.star()),
                        searchStartOrderDate(myReview.startDate()),
                        searchEndOrderDate(myReview.endDate())
                )
                .orderBy(review.updatedAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
    
    /**
     * ReviewId 기반으로 ReviewImage 데이터 조회 (Map<key , value> 형태로)
     * */
    private Map<Long, List<ReviewResponse.ImageInfo>> fetchReviewImages(List<Long> reviewIds) {
        return queryFactory
                .select(Projections.constructor(ReviewResponse.ImageInfo.class,
                        reviewImage.review.id,
                        reviewImage.imageUrl
                ))
                .from(reviewImage)
                .where(reviewImage.review.id.in(reviewIds))
                .transform(GroupBy.groupBy(reviewImage.review.id).as(GroupBy.list(
                        Projections.constructor(ReviewResponse.ImageInfo.class,
                                reviewImage.id,
                                reviewImage.imageUrl)
                )));
    }
    
    /**
     * List<ReviewResponse.Info> 구조 잡기
     * */
    private List<ReviewResponse.Info> mapReviewsToResponse(List<Review> reviews, Map<Long, List<ReviewResponse.ImageInfo>> reviewImagesMap) {
        return reviews.stream()
                .map(r -> new ReviewResponse.Info(
                        r.getTitle(),
                        r.getContent(),
                        r.getStar(),
                        r.getCreatedAt(),
                        r.getUpdatedAt(),
                        reviewImagesMap.getOrDefault(r.getId(), Collections.emptyList())
                ))
                .toList();
    }
    
    /**
     * review 기준 데이터 총 개수 구하기
     * */
    private Long fetchTotalCount(Long userId, Long storeId, ReviewRequest.MyReview myReview) {
        return queryFactory
                .select(review.count())
                .from(review)
                .where(
                        review.orders.user.id.eq(userId),
                        storeId != null ? review.orders.store.id.eq(storeId) : null,
                        starCondition(myReview.star()),
                        searchStartOrderDate(myReview.startDate()),
                        searchEndOrderDate(myReview.endDate())
                )
                .fetchOne();
    }

    /**
     * 별점 조건문
     * */
    private BooleanExpression starCondition(Long star) {
        return star != null ? review.star.eq(Double.valueOf(star)) : null;
    }
    
    /**
     * 기간 조건문 시작일
     * */
    private BooleanExpression searchStartOrderDate(LocalDate startDate) {
        return startDate != null ? review.createdAt.goe(startDate.atStartOfDay()) : null;
    }
    
    /**
     * 기간 조건문 종료일
     * */
    private BooleanExpression searchEndOrderDate(LocalDate endDate) {
        return endDate != null ? review.createdAt.loe(endDate.atStartOfDay()) : null;
    }
}
