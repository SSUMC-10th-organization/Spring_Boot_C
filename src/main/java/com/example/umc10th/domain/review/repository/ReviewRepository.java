package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 가게(Store) 객체를 기준으로 페이징된 리뷰 목록을 가져옵니다.
    Page<Review> findAllByStore(Store store, Pageable pageable);
}
