package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewRequestDTO;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.service.ReviewCommandService;
import com.example.umc10th.domain.review.service.ReviewQueryService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReviewController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService; // 아래부터 추가됨

    @PostMapping("/stores/{storeId}/reviews")
    public ApiResponse<ReviewResponseDTO.ReviewCreateResultDTO> createReview(
            @PathVariable Long storeId,
            @RequestBody @Valid ReviewRequestDTO.ReviewCreateDTO request
    ) {
        Review review = reviewCommandService.createReview(1L, storeId, request.getRating(), request.getContent());
        return ApiResponse.onSuccess(ReviewConverter.toReviewCreateResultDTO(review.getId(), review.getRating(), review.getContent()));
    }

    // 기존 코드는 냅둠
    @GetMapping("/reviews/me")
    public ApiResponse<ReviewResponseDTO.MyReviewListDTO> getMyReviews(
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        return ApiResponse.onSuccess(ReviewConverter.toMyReviewListDTO(page, size));
    }

    // 7주차 [미션 2번] 새로운 커서 기반 페이징 API 추가
    @GetMapping("/reviews/me/cursor")
    public ApiResponse<ReviewResponseDTO.MyReviewCursorListDTO> getMyReviewsCursor(
            @RequestParam(required = false) Long cursorId,       // 커서 ID
            @RequestParam(required = false) Integer cursorRating,// 커서 별점
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sortBy     // "id" 또는 "rating"
    ) {
        // 임시로 1번 유저(1L)라고 가정
        Slice<Review> reviewSlice = reviewQueryService.getMyReviewsByCursor(1L, cursorId, cursorRating, sortBy, size);
        return ApiResponse.onSuccess(ReviewConverter.toMyReviewCursorListDTO(reviewSlice));
    }
}
