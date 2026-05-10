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

    @Operation(summary = "내가 작성한 리뷰 목록 조회 (커서 기반 페이지네이션)",
            description = "sortBy=id (ID순, 기본값) 또는 sortBy=star (별점순). 첫 요청은 lastId/lastStar 없이, 다음 요청은 응답의 nextCursorId/nextCursorStar를 사용")
    @GetMapping("/my")
    ResponseEntity<ApiResponse<ReviewResponseDto.MyReviewListResult>> getMyReviews(
            @RequestHeader("X-User-Id") Long userId,
            @RequestParam(required = false) Long lastId,
            @RequestParam(required = false) Integer lastStar,
            @RequestParam(defaultValue = "id") String sortBy);
}
