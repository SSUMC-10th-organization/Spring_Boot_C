package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Page;

import java.util.List;

public class MemberConverter {

    // 마이페이지 회원 정보
    public static MemberResDTO.GetInfo toGetInfo(Member member) {
        return MemberResDTO.GetInfo.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .profileUrl(member.getProfileUrl())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getPoint())
                .build();
    }

    // 리뷰 한건
    public static MemberResDTO.ReviewPreview toReviewPreview(Review review) {
        return MemberResDTO.ReviewPreview.builder()
                .reviewId(review.getId())
                .content(review.getContent())
                .star(review.getStar().doubleValue())
                .storeName(review.getStore().getName())
                .build();
    }

    // 리뷰 페이징 응답
    public static MemberResDTO.ReviewList toReviewList(Page<Review> reviewPage) {
        List<MemberResDTO.ReviewPreview> reviewList = reviewPage.getContent().stream()
                .map(MemberConverter::toReviewPreview)
                .toList();

        return MemberResDTO.ReviewList.builder()
                .reviewList(reviewList)
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .build();
    }
}