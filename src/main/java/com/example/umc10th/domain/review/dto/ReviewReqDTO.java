package com.example.umc10th.domain.review.dto;

import lombok.Getter;

import java.math.BigDecimal;

public class ReviewReqDTO {
    @Getter
    public static class CreateReviewDTO {
        BigDecimal score;
        String reviewContent;
    }
}
