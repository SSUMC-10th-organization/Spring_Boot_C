package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Slice;

public interface ReviewQueryService {
    Slice<Review> getMyReviewsByCursor(Long userId, Long cursorId, Integer cursorRating, String sortBy, Integer size);
}
