package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewRequestDTO;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ReviewController {

    @PostMapping("/missions/{missionId}/reviews")
    public ApiResponse<ReviewResponseDTO.ReviewCreateResultDTO> createReview(
            @PathVariable Long missionId,
            @RequestBody ReviewRequestDTO.ReviewCreateDTO request
    ) {
        ReviewResponseDTO.ReviewCreateResultDTO result = ReviewResponseDTO.ReviewCreateResultDTO.builder()
                .reviewId(1L)
                .missionId(missionId)
                .rating(request.getRating())
                .content(request.getContent())
                .build();

        return ApiResponse.onSuccess(result);
    }

    @GetMapping("/reviews/me")
    public ApiResponse<ReviewResponseDTO.MyReviewListDTO> getMyReviews(
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        ReviewResponseDTO.MyReviewListDTO result = ReviewResponseDTO.MyReviewListDTO.builder()
                .reviews(List.of(
                        ReviewResponseDTO.ReviewPreviewDTO.builder()
                                .reviewId(1L)
                                .rating(5)
                                .content("음식이 맛있고 친절했어요.")
                                .build(),
                        ReviewResponseDTO.ReviewPreviewDTO.builder()
                                .reviewId(2L)
                                .rating(4)
                                .content("재방문 의사 있어요.")
                                .build()
                ))
                .page(page)
                .size(size)
                .build();

        return ApiResponse.onSuccess(result);
    }
}
