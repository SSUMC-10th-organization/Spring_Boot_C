package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

public class ReviewRequestDTO {

    @Getter
    public static class ReviewCreateDTO {
        // 7주차 3번 미션: 검증 어노테이션 추가
        @NotNull(message = "별점은 필수입니다.")
        @Min(value = 1, message = "별점은 1점 이상이어야 합니다.")
        @Max(value = 5, message = "별점은 5점 이하여야 합니다.")
        private Integer rating;

        @NotBlank(message = "리뷰 내용은 공백일 수 없습니다.")
        private String content;

        private List<String> imageUrls;
    }
}
