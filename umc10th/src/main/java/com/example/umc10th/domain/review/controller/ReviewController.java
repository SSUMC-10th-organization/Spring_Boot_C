package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 작성
    // POST /stores/{storeId}/reviews
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewDTO> createReview(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long storeId,
            @RequestBody ReviewReqDTO.CreateReviewDTO request
    ) {
        // 임시로 memberId를 헤더에서 파싱 (JWT 미구현 시)
        Long memberId = Long.parseLong(authorization);

        return ApiResponse.onSuccess(
                ReviewSuccessCode.CREATE_REVIEW_SUCCESS,
                reviewService.createReview(storeId, memberId, request)
        );
    }
}