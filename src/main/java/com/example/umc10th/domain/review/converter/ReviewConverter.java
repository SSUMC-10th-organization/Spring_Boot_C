package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import java.util.List;

public class ReviewConverter {

    public static ReviewResponseDTO.ReviewCreateResultDTO toReviewCreateResultDTO(Long missionId, Integer rating, String content) {
        return ReviewResponseDTO.ReviewCreateResultDTO.builder()
                .reviewId(1L)
                .missionId(missionId)
                .rating(rating)
                .content(content)
                .build();
    }

    public static ReviewResponseDTO.MyReviewListDTO toMyReviewListDTO(Integer page, Integer size) {
        return ReviewResponseDTO.MyReviewListDTO.builder()
                .reviews(List.of(
                        ReviewResponseDTO.ReviewPreviewDTO.builder().reviewId(1L).rating(5).content("음식이 맛있고 친절했어요.").build(),
                        ReviewResponseDTO.ReviewPreviewDTO.builder().reviewId(2L).rating(4).content("재방문 의사 있어요.").build()
                ))
                .page(page)
                .size(size)
                .build();
    }
}
