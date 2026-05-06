package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReviewResDTO {
    @Builder
    public record CreateReviewDTO(
            Long reviewId,
            BigDecimal score,
            String reviewContent,
            LocalDate createdAt
    ) {}
}
