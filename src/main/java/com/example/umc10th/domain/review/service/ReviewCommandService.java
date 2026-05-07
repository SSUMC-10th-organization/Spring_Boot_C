package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.entity.Review;

public interface ReviewCommandService {
    // 유저 ID, 가게 ID, 별점, 내용을 받아서 리뷰를 생성하고 저장합니다.
    Review createReview(Long userId, Long storeId, Integer rating, String content);
}
