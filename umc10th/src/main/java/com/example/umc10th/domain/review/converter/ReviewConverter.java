package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.store.entity.Store;
import java.math.BigDecimal;

public class ReviewConverter {

    // ReqDTO + Store + Member → Review 엔티티로 변환
    public static Review toReview(ReviewReqDTO.CreateReviewDTO request, Store store, Member member) {
        return Review.builder()
                .store(store)
                .member(member)
                .content(request.content()) // requset는 record라 get떼야됨
                .star(BigDecimal.valueOf(request.star())) // requset는 record라 get떼야됨
                .build();
    }

    // 저장된 Review 엔티티 → ResDTO로 변환
    // 얜 응답 내려주는거니까, getter 땜에 get붙이지
    public static ReviewResDTO.CreateReviewDTO toCreateReviewDTO(Review review) {
        return ReviewResDTO.CreateReviewDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }
}