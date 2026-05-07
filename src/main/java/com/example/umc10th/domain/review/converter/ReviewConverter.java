package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewRequestDto;
import com.example.umc10th.domain.review.dto.ReviewResponseDto;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.entity.ReviewFeedback;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.user.entity.User;
import org.springframework.data.domain.Slice;

import java.util.List;

public class ReviewConverter {

    public static ReviewResponseDto.ReviewItem toReviewItem(Review review) {
        return new ReviewResponseDto.ReviewItem(
                review.getId(),
                review.getUser().getName(),
                review.getStar(),
                review.getCreatedAt(),
                review.getContent()
        );
    }

    public static ReviewResponseDto.ReviewListResult toReviewListResult(Slice<Review> slice) {
        List<ReviewResponseDto.ReviewItem> items = slice.getContent().stream()
                .map(ReviewConverter::toReviewItem)
                .toList();
        return new ReviewResponseDto.ReviewListResult(items, slice.hasNext());
    }

    public static Review toReview(User user, Store store, ReviewRequestDto.AddReview request) {
        return Review.builder()
                .user(user)
                .store(store)
                .content(request.content())
                .star(request.star())
                .build();
    }

    public static ReviewResponseDto.AddReviewResult toAddReviewResult(Review review) {
        return new ReviewResponseDto.AddReviewResult(
                review.getId(),
                review.getStar(),
                review.getContent(),
                review.getCreatedAt()
        );
    }

    public static ReviewFeedback toFeedback(Review review, String content) {
        return ReviewFeedback.builder()
                .review(review)
                .content(content)
                .build();
    }

    public static ReviewResponseDto.AddFeedbackResult toAddFeedbackResult(ReviewFeedback feedback) {
        return new ReviewResponseDto.AddFeedbackResult(feedback.getId());
    }
}
