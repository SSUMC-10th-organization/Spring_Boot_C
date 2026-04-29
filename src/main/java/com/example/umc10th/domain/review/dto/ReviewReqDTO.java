package com.example.umc10th.domain.review.dto;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

public class ReviewReqDTO {
    @Getter
    public static class CreativeReviewDTO {
        Float score;
        String reviewContent;
    }
}
