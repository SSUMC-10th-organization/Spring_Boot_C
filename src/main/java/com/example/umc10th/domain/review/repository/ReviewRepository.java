package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // ID 순 - 첫 요청 (커서 없음)
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId ORDER BY r.id DESC")
    Slice<Review> findByMemberId(
            @Param("memberId") Long memberId,
            Pageable pageable
    );

    // ID 순 - 커서 있음
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId AND r.id < :cursor ORDER BY r.id DESC")
    Slice<Review> findByMemberIdAndIdCursor(
            @Param("memberId") Long memberId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );

    // 별점 순 - 첫 요청 (커서 없음)
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId ORDER BY r.star DESC, r.id DESC")
    Slice<Review> findByMemberIdOrderByStar(
            @Param("memberId") Long memberId,
            Pageable pageable
    );

    // 별점 순 - 커서 있음
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId AND (r.star < :star OR (r.star = :star AND r.id < :id)) ORDER BY r.star DESC, r.id DESC")
    Slice<Review> findByMemberIdAndStarCursor(
            @Param("memberId") Long memberId,
            @Param("star") BigDecimal star,
            @Param("id") Long id,
            Pageable pageable
    );

}