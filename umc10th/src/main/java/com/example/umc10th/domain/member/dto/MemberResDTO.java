package com.example.umc10th.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class MemberResDTO {

    @Builder
    public record SignUpDTO(
            Long memberId,
            String email
    ) {}

    @Builder
    public record Login(
            String accessToken
    ){}

    // DTO 안에 inner class 이름은 DTO 접미사 안붙이는 일반적이다.
    // 마이페이지 -회원정보
    @Builder
    public record MyPageInfo(
           //Long memberId, 관례상 내려주고 + 응답데이터 자체 식별하는데 쓰임
            String nickname,
            String email,
            String phoneNumber,
            Integer point,
            String profileUrl
    ) {}

    // 리뷰 한건
    @Builder
    public record MyPageReview(
            Long reviewId,
            String content,
            Double star,
            String storeName
    ) {}

    // 리뷰 페이징 응답
    @Builder
    public record MyPageReviewList(
            List<MyPageReview> reviewList,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    // 6주차_미션2 - 커서 기반 페이지네이션 응답
    @Builder
    public record MyPageReviewCursorList(
            List<MyPageReview> reviewList,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {}
}