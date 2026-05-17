package com.example.umc10th.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ReviewResDTO {


    @Builder
    public record CreateReviewDTO(
            Long reviewId,
            String memberName,
            BigDecimal score,
            String reviewContent,
            LocalDate createdAt
    ) {}

    @Builder
    @Getter
    public static class Pagination<T> {
        private List<T> data;
        private Boolean hasNext;
        private String nextCursor;
        private Integer pageSize;
    }
}
