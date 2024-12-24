package com.personal.domain.review.event.handler;

import com.personal.domain.review.event.ReviewImageRemoveEvent;
import com.personal.domain.review.event.ReviewImageSaveEvent;
import com.personal.domain.review.event.ReviewImageUpdateEvent;
import com.personal.domain.review.service.ReviewImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReviewImageEventListener {
    private final ReviewImageService reviewImageService;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handleReviewImageSaveEvent(ReviewImageSaveEvent event) throws Exception {
        log.info("handleReviewImageSaveEvent");
        reviewImageService.save(event.review() , event.files());
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handleReviewImageUpdateEvent(ReviewImageUpdateEvent event) throws Exception {
        log.info("handleReviewImageUpdateEvent");
        reviewImageService.update(event.review() , event.imageNumber() , event.files());
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handleReviewImageRemoveEvent(ReviewImageRemoveEvent event) throws Exception {
        log.info("handleReviewImageRemoveEvent");
        reviewImageService.remove(event.review());
    }
}
