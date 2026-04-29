package com.example.umc10th.domain.review.dto;

import lombok.Getter;

import java.util.List;

public class ReviewRequestDTO {

    @Getter
    public static class ReviewCreateDTO {
        private Integer rating;
        private String content;
        private List<String> imageUrls;
    }
}
