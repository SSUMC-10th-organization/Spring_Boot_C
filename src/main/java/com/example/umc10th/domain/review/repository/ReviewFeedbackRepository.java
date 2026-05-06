package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.ReviewFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewFeedbackRepository extends JpaRepository<ReviewFeedback, Long> {
}
