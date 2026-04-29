package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.RequestToViewNameTranslator;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")

public class ReviewController {

    @PostMapping("/{storeId}")
    public ApiResponse<ReviewResDTO.CreateReviewDTO> createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewReqDTO.CreativeReviewDTO request
    ){
        return ApiResponse.onSuccess(ReviewSuccessCode.CREATE_REVIEW_SUCCESS, null);
    }



}
