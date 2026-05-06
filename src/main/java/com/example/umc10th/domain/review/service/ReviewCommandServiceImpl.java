package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.User;
import com.example.umc10th.domain.member.repository.UserRepository;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Override
    public Review createReview(Long userId, Long storeId, Integer rating, String content) {
        // 1. 유저와 가게가 존재하는지 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 가게가 존재하지 않습니다."));

        // 2. 리뷰 엔티티 생성 (Builder 패턴 사용)
        Review review = Review.builder()
                .user(user)
                .store(store)
                .rating(rating)
                .content(content)
                .build();

        // 3. DB에 저장 후 반환
        return reviewRepository.save(review);
    }
}
