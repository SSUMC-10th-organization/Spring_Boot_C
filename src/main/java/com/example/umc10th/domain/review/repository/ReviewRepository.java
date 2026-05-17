package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Slice<Review> findAllByStore_Id(Long storeId, Pageable pageable);

    // 내 리뷰 조회 - ID순 (첫 페이지)
    Slice<Review> findAllByUser_Id(Long userId, Pageable pageable);

    // 내 리뷰 조회 - ID순 (다음 페이지, lastId 커서)
    Slice<Review> findAllByUser_IdAndIdLessThan(Long userId, Long lastId, Pageable pageable);

    // 내 리뷰 조회 - 별점순 (첫 페이지)
    @Query("SELECT r FROM Review r WHERE r.user.id = :userId ORDER BY r.star DESC, r.id DESC")
    Slice<Review> findAllByUserIdOrderByStar(@Param("userId") Long userId, Pageable pageable);

    // 내 리뷰 조회 - 별점순 (다음 페이지, lastStar + lastId 복합 커서)
    @Query("SELECT r FROM Review r WHERE r.user.id = :userId AND (r.star < :lastStar OR (r.star = :lastStar AND r.id < :lastId)) ORDER BY r.star DESC, r.id DESC")
    Slice<Review> findAllByUserIdOrderByStarWithCursor(@Param("userId") Long userId, @Param("lastStar") Integer lastStar, @Param("lastId") Long lastId, Pageable pageable);
}
