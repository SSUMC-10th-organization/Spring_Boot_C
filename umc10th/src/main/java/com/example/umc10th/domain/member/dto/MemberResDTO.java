package com.example.umc10th.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class MemberResDTO {

    @Builder
    public record SignUpDTO(
            Long memberId
    ) {}

    // 마이페이지 -회원정보

    @Builder
    public record GetInfo(
            Long memberId,
            String nickname,
            String email,
            String phoneNumber,
            Integer point,
            String profileUrl
    ) {}

    // 리뷰 한건
    @Getter
    @Builder
    public static class ReviewPreview {
        private Long reviewId;
        private String content;
        private Double star;
        private String storeName;
    }

    // 리뷰 페이징 응답
    @Getter
    @Builder
    public static class ReviewList {
        private List<ReviewPreview> reviewList;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }

}