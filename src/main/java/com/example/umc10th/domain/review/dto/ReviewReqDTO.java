package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

import java.math.BigDecimal;

public class ReviewReqDTO {
    @Getter
    public static class CreateReviewDTO {
        @NotNull(message = "별점은 필수입니다.")
        @DecimalMin(value = "0.0", message = "별점은 0.0 이상이어야 합니다.")
        @DecimalMax(value = "5.0", message = "별점은 5.0 이하이어야 합니다.")
        BigDecimal score;
        @NotBlank(message = "리뷰 내용은 필수입니다.")
        @Size(min = 1, max = 500, message = "리뷰 내용은 1자 이상 500자 이하이어야 합니다.")
        String reviewContent;
    }
}
