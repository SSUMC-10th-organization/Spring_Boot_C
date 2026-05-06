package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewRequestDTO;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.service.ReviewCommandService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor // 추가: 생성자 주입을 위해 필요합니다.
@RequestMapping("/api/v1")
public class ReviewController {

    private final ReviewCommandService reviewCommandService; // 추가: 아까 만든 서비스 연결

    @PostMapping("/stores/{storeId}/reviews") // URL을 가게(Store) 기준으로 수정했습니다 (리뷰는 가게에 달리므로!)
    public ApiResponse<ReviewResponseDTO.ReviewCreateResultDTO> createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewRequestDTO.ReviewCreateDTO request
    ) {
        // 임시로 request DTO 안에 userId 1L가 있다고 가정했습니다.
        // 서비스의 createReview 메서드를 호출해서 DB에 실제 저장!
        Review review = reviewCommandService.createReview(
                1L, // DTO에 userId가 없다면 1L 같은 임시값을 넣으셔도 됩니다.
                storeId,
                request.getRating(),
                request.getContent()
        );

        // Converter를 통해 저장된 엔티티 데이터를 DTO로 변환하여 응답
        return ApiResponse.onSuccess(
                ReviewConverter.toReviewCreateResultDTO(review.getId(), review.getRating(), review.getContent())
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
