package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewRequestDTO;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ReviewController {

    @PostMapping("/missions/{missionId}/reviews")
    public ApiResponse<ReviewResponseDTO.ReviewCreateResultDTO> createReview(
            @PathVariable Long missionId,
            @RequestBody ReviewRequestDTO.ReviewCreateDTO request
    ) {
        return ApiResponse.onSuccess(
                ReviewConverter.toReviewCreateResultDTO(missionId, request.getRating(), request.getContent())
        );
    }

    @GetMapping("/reviews/me")
    public ApiResponse<ReviewResponseDTO.MyReviewListDTO> getMyReviews(
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        return ApiResponse.onSuccess(ReviewConverter.toMyReviewListDTO(page, size));
    }
}
