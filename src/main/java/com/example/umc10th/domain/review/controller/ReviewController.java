package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")

public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{storeId}")
    public ApiResponse<ReviewResDTO.CreateReviewDTO> createReview(
            @PathVariable Long storeId,
            @RequestParam Long memberId,
            @RequestBody ReviewReqDTO.CreateReviewDTO request
    ){
        return ApiResponse.onSuccess(ReviewSuccessCode.CREATE_REVIEW_SUCCESS,
                reviewService.createReview(memberId, storeId, request));
    }



}
