package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewRequestDto;
import com.example.umc10th.domain.review.dto.ReviewResponseDto;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.entity.ReviewFeedback;
import com.example.umc10th.domain.review.enums.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewFeedbackRepository;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.repository.StoreRepository;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.repository.UserRepository;
import com.example.umc10th.global.apipayload.code.GeneralErrorCode;
import com.example.umc10th.global.apipayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewFeedbackRepository reviewFeedbackRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    @Transactional
    public ReviewResponseDto.AddReviewResult addReview(Long userId, ReviewRequestDto.AddReview request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.USER_NOT_FOUND));
        Store store = storeRepository.findById(request.storeId())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.STORE_NOT_FOUND));
        Review review = ReviewConverter.toReview(user, store, request);
        reviewRepository.save(review);
        return ReviewConverter.toAddReviewResult(review);
    }

    public ReviewResponseDto.ReviewListResult getStoreReviews(Long storeId, int page) {
        if (!storeRepository.existsById(storeId)) {
            throw new GeneralException(GeneralErrorCode.STORE_NOT_FOUND);
        }
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
        Slice<Review> slice = reviewRepository.findAllByStore_Id(storeId, pageable);
        return ReviewConverter.toReviewListResult(slice);
    }

    @Transactional
    public ReviewResponseDto.AddFeedbackResult addFeedback(Long reviewId, ReviewRequestDto.AddFeedback request) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new GeneralException(ReviewErrorCode.REVIEW_NOT_FOUND));
        ReviewFeedback feedback = ReviewConverter.toFeedback(review, request.content());
        reviewFeedbackRepository.save(feedback);
        return ReviewConverter.toAddFeedbackResult(feedback);
    }
}
