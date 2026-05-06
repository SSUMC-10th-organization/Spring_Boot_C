package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewRequestDto;
import com.example.umc10th.domain.review.dto.ReviewResponseDto;
import com.example.umc10th.global.apipayload.handler.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Review", description = "리뷰 관련 API")
@RequestMapping("/api/reviews")
public interface ReviewControllerDocs {

    @Operation(summary = "리뷰 작성")
    @PostMapping
    ResponseEntity<ApiResponse<ReviewResponseDto.AddReviewResult>> addReview(
            @RequestHeader("X-User-Id") Long userId,
            @Valid @RequestBody ReviewRequestDto.AddReview request);

    @Operation(summary = "점포 리뷰 목록 조회")
    @GetMapping("/store/{storeId}")
    ResponseEntity<ApiResponse<ReviewResponseDto.ReviewListResult>> getStoreReviews(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "0") int page);

    @Operation(summary = "리뷰 답글 작성")
    @PostMapping("/{reviewId}/feedback")
    ResponseEntity<ApiResponse<ReviewResponseDto.AddFeedbackResult>> addFeedback(
            @PathVariable Long reviewId,
            @Valid @RequestBody ReviewRequestDto.AddFeedback request);
}
