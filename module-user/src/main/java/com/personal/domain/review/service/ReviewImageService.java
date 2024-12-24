package com.personal.domain.review.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.personal.common.component.FileManagement;
import com.personal.common.dto.FileUploadDto;
import com.personal.domain.review.repository.ReviewImageRepository;
import com.personal.entity.review.Review;
import com.personal.entity.review.ReviewImage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewImageService {

    private final FileManagement fileManagement;
    private final AmazonS3Client amazonS3Client;

    private final ReviewImageRepository reviewImageRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public void save(Review review , List<MultipartFile> files) {
        try {
            for (MultipartFile file : files) {
                // 리뷰 이미지 처리
                awsS3Upload(file).thenAccept(url -> {
                    log.info("Uploading file {} to S3", url);

                    // 리뷰 이미지 등록
                    ReviewImage reviewImage = new ReviewImage(url , review);
                    reviewImageRepository.save(reviewImage);
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Async
    protected CompletableFuture<String> awsS3Upload(MultipartFile file) throws IOException {
        FileUploadDto fileUploadDto = fileManagement.storeFile(file);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(fileUploadDto.getFileSize());
        metadata.setContentType(fileUploadDto.getContentType());
        amazonS3Client.putObject(bucket, fileUploadDto.getConvertFileName(), file.getInputStream(), metadata);
        return CompletableFuture.completedFuture(amazonS3Client.getUrl(bucket, fileUploadDto.getConvertFileName()).toString());
    }
}
