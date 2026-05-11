package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.member.entity.User;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 가게(Store) 객체를 기준으로 페이징된 리뷰 목록을 가져옵니다.
    Page<Review> findAllByStore(Store store, Pageable pageable);

    // 7주차 [미션 2번] ID 기준 커서 기반 페이징 (최신순)
    Slice<Review> findAllByUserOrderByIdDesc(User user, Pageable pageable); // 최초 조회
    Slice<Review> findAllByUserAndIdLessThanOrderByIdDesc(User user, Long id, Pageable pageable); // 커서 조회

    // 7주차 [미션 2번] 별점 기준 커서 기반 페이징 (별점 높은 순 -> 동점일 경우 ID 최신순)
    Slice<Review> findAllByUserOrderByRatingDescIdDesc(User user, Pageable pageable); // 최초 조회
    @Query("SELECT r FROM Review r WHERE r.user = :user AND (r.rating < :rating OR (r.rating = :rating AND r.id < :id)) ORDER BY r.rating DESC, r.id DESC")
    Slice<Review> findByRatingCursor(@Param("user") User user, @Param("rating") Integer rating, @Param("id") Long id, Pageable pageable); // 커서 조회
}
