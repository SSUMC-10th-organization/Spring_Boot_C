package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.User;
import com.example.umc10th.domain.member.repository.UserRepository;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @Override
    public Slice<Review> getMyReviewsByCursor(Long userId, Long cursorId, Integer cursorRating, String sortBy, Integer size) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        PageRequest pageRequest = PageRequest.of(0, size); // 커서는 항상 0페이지 호출

        if ("rating".equalsIgnoreCase(sortBy)) {
            // 별점 순 커서
            if (cursorRating == null || cursorId == null) {
                return reviewRepository.findAllByUserOrderByRatingDescIdDesc(user, pageRequest);
            }
            return reviewRepository.findByRatingCursor(user, cursorRating, cursorId, pageRequest);
        } else {
            // ID 최신순 커서 (기본값)
            if (cursorId == null) {
                return reviewRepository.findAllByUserOrderByIdDesc(user, pageRequest);
            }
            return reviewRepository.findAllByUserAndIdLessThanOrderByIdDesc(user, cursorId, pageRequest);
        }
    }
}
