package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResponseDTO.ReviewCreateResultDTO toReviewCreateResultDTO(Long missionId, Integer rating, String content) {
        return ReviewResponseDTO.ReviewCreateResultDTO.builder()
                .reviewId(1L)
                .missionId(missionId)
                .rating(rating)
                .content(content)
                .build();
    }

    public static ReviewResponseDTO.MyReviewListDTO toMyReviewListDTO(Integer page, Integer size) {
        return ReviewResponseDTO.MyReviewListDTO.builder()
                .reviews(List.of(
                        ReviewResponseDTO.ReviewPreviewDTO.builder().reviewId(1L).rating(5).content("음식이 맛있고 친절했어요.").build(),
                        ReviewResponseDTO.ReviewPreviewDTO.builder().reviewId(2L).rating(4).content("재방문 의사 있어요.").build()
                ))
                .page(page)
                .size(size)
                .build();
    }

    public static ReviewResponseDTO.MyReviewCursorDTO toMyReviewCursorDTO(Review review) {
        return ReviewResponseDTO.MyReviewCursorDTO.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .nickname(review.getUser().getName())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt().toLocalDate().toString())
                // 7주차 미션 2 - 사장님 답글 추가 (현재 DB에 없으므로 임시로 null 처리)
                .ownerReply(null)
                .build();
    }

    public static ReviewResponseDTO.MyReviewCursorListDTO toMyReviewCursorListDTO(Slice<Review> slice) {
        List<ReviewResponseDTO.MyReviewCursorDTO> dtoList = slice.stream()
                .map(ReviewConverter::toMyReviewCursorDTO)
                .collect(Collectors.toList());

        Long nextCursorId = null;
        Integer nextCursorRating = null;

        if (slice.hasNext() && !dtoList.isEmpty()) {
            ReviewResponseDTO.MyReviewCursorDTO lastElement = dtoList.get(dtoList.size() - 1);
            nextCursorId = lastElement.getReviewId();
            nextCursorRating = lastElement.getRating();
        }

        return ReviewResponseDTO.MyReviewCursorListDTO.builder()
                .reviews(dtoList)
                .listSize(dtoList.size())
                .hasNext(slice.hasNext())
                .nextCursorId(nextCursorId)
                .nextCursorRating(nextCursorRating)
                .build();
    }
}
