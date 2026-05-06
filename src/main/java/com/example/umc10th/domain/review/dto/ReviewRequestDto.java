package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewRequestDto {

    public record Create() {
    }

    public record AddReview(
            @NotNull Long storeId,
            @NotBlank String content,
            @NotNull @Min(1) @Max(5) Integer star
    ) {
    }

    public record AddFeedback(
            @NotBlank String content
    ) {
    }
}
