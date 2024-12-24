package com.personal.domain.review.event.handler;

import com.personal.domain.review.event.ReviewImageSaveEvent;
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
    public void handleReviewImageEvent(ReviewImageSaveEvent event) throws Exception {
        log.info("handleReviewImageEvent");
        reviewImageService.save(event.review() , event.files());
    }
}
