package com.example.umc10th.domain.review.dto;

import lombok.Getter;
import java.util.List;

public class ReviewReqDTO {

    // 리뷰 작성
    public record CreateReviewDTO(
            Double star,
            String content,
            List<String> photoUrls
    ) {}
}