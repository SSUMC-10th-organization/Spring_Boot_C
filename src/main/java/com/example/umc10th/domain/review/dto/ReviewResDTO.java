package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDate;

public class ReviewResDTO {
    @Builder
    public record CreateReviewDTO(
            Long reviewId,
            Float score,
            String reviewContent,
            LocalDate createdAt
    ) {}
}
