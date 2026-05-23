package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 회원가입 쿼리메서드 정의
    Optional<Member> findByEmail(String email);

    // 특정 회원이 작성한 리뷰 페이징 조회
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId ORDER BY r.createdAt DESC")
    Page<Review> findReviewsByMemberId(@Param("memberId") Long memberId, Pageable pageable);

    // 1) 커서 기반 (ID 순)
    // 첫 페이지 (커서 없음)
    @Query("""
        SELECT r FROM Review r WHERE r.member.id = :memberId ORDER BY r.id DESC
    """)
    Slice<Review> findReviewsByMemberIdOrderByIdDesc(
            @Param("memberId") Long memberId,
            Pageable pageable
    );

    // 다음 페이지 (커서 있음)
    @Query("""
        SELECT r FROM Review r 
        WHERE r.member.id = :memberId 
          AND r.id < :idCursor 
        ORDER BY r.id DESC
    """)
    Slice<Review> findReviewsByMemberIdAndIdLessThanOrderByIdDesc(
            @Param("memberId") Long memberId,
            @Param("idCursor") Long idCursor,
            Pageable pageable
    );

    // 2)  커서 기반 (별점 순)
    // 첫 페이지 (커서 없음)
    @Query("""
        SELECT r FROM Review r 
        WHERE r.member.id = :memberId 
        ORDER BY r.star DESC, r.id DESC
    """)
    Slice<Review> findReviewsByMemberIdOrderByStarDesc(
            @Param("memberId") Long memberId,
            Pageable pageable
    );

    // 다음 페이지 (커서 있음 - 별점 같으면 ID로 추가 비교)
    @Query("""
        SELECT r FROM Review r 
        WHERE r.member.id = :memberId 
          AND (r.star < :starCursor 
               OR (r.star = :starCursor AND r.id < :idCursor))
        ORDER BY r.star DESC, r.id DESC
    """)
    Slice<Review> findReviewsByMemberIdAndStarCursor(
            @Param("memberId") Long memberId,
            @Param("starCursor") BigDecimal starCursor,
            @Param("idCursor") Long idCursor,
            Pageable pageable
    );


}