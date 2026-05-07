package com.example.umc10th.domain.review.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponseDto {

    public record Result() {
    }

    public record ReviewItem(
            Long reviewId,
            String nickname,
            Integer star,
            LocalDateTime createdAt,
            String content
    ) {
    }

    public record ReviewListResult(
            List<ReviewItem> reviewList,
            Boolean hasNext
    ) {
    }

    public record AddReviewResult(
            Long reviewId,
            Integer star,
            String content,
            LocalDateTime createdAt
    ) {
    }

    public record AddFeedbackResult(
            Long feedbackId
    ) {
    }
}
