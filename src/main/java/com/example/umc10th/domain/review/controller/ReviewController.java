package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewRequestDto;
import com.example.umc10th.domain.review.dto.ReviewResponseDto;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apipayload.code.GeneralSuccessCode;
import com.example.umc10th.global.apipayload.handler.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController implements ReviewControllerDocs {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ApiResponse<ReviewResponseDto.AddReviewResult>> addReview(
            @RequestHeader("X-User-Id") Long userId,
            @Valid @RequestBody ReviewRequestDto.AddReview request) {
        return ResponseEntity.ok(ApiResponse.onSuccess(GeneralSuccessCode.CREATED, reviewService.addReview(userId, request)));
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<ApiResponse<ReviewResponseDto.ReviewListResult>> getStoreReviews(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(ApiResponse.onSuccess(GeneralSuccessCode.OK, reviewService.getStoreReviews(storeId, page)));
    }

    @PostMapping("/{reviewId}/feedback")
    public ResponseEntity<ApiResponse<ReviewResponseDto.AddFeedbackResult>> addFeedback(
            @PathVariable Long reviewId,
            @Valid @RequestBody ReviewRequestDto.AddFeedback request) {
        return ResponseEntity.ok(ApiResponse.onSuccess(GeneralSuccessCode.CREATED, reviewService.addFeedback(reviewId, request)));
    }
}
