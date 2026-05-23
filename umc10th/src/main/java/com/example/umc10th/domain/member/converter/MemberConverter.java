package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.Term;
import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import com.example.umc10th.domain.member.entity.mapping.MemberTerm;
import com.example.umc10th.domain.member.enums.Sex;
import com.example.umc10th.domain.member.enums.SocialType;
import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

public class MemberConverter {


    // 회원가입 요청DTO -> Member 엔티티
    public static Member toMember(MemberReqDTO.SignUpDTO request, PasswordEncoder passwordEncoder) {
        return Member.builder()
                .name(request.getName())
                .nickname(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .sex(request.getSex())
                .birth(LocalDate.parse(request.getBirth()))
                .address(request.getAddress())
                .point(0)
                .isLeft(false)
                .socialUid("")
                .socialType(SocialType.LOCAL)
                .profileUrl("")
                .build();
    }


    // DB에 저장된 엔터티-> 응답DTO
    public static MemberResDTO.SignUpDTO toSignUpDTO(Member member) {
        return MemberResDTO.SignUpDTO.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .build();
    }

    // 회원가입 시 음식 저장
    public static MemberFood toMemberFood(Member member, Food food) {
        return MemberFood.builder()
                .member(member)
                .food(food)
                .build();
    }

    // 회원가입 시 약관저장
    public static MemberTerm toMemberTerm(Member member, Term term) {
        return MemberTerm.builder()
                .member(member)
                .term(term)
                .build();
    }

    // 마이페이지 회원 정보
    public static MemberResDTO.MyPageInfo toMyPageInfo(Member member) {
        return MemberResDTO.MyPageInfo.builder()
                // 1. MemberResDTO에서 @Builder어노테이션으로 builder()메서드 만들어줌
                // 2. 빌더 객체 반환
                // 3. 필드명과 동일한 setter 같은 메서드들 만들어짐 .nickname(), .profileUrl()..
                // 4. .build() 최종적으로 GetInfo 객체를 만들어서 반환

                //.memberId(member.getId())
                .nickname(member.getNickname()) // member엔터티의 @Getter 어노테이션으로 메서드 자동 생성
                .profileUrl(member.getProfileUrl())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getPoint())
                .build();
    }

    // 리뷰 한건
    public static MemberResDTO.MyPageReview toReviewPreview(Review review) {
        return MemberResDTO.MyPageReview.builder()
                .reviewId(review.getId())
                .content(review.getContent())
                .star(review.getStar().doubleValue())
                .storeName(review.getStore().getName())
                .build();
    }

    // 리뷰 페이징 응답
    public static MemberResDTO.MyPageReviewList toReviewList(Page<Review> reviewPage) {
        List<MemberResDTO.MyPageReview> reviewList = reviewPage.getContent().stream()
                .map(MemberConverter::toReviewPreview)
                .toList();

        return MemberResDTO.MyPageReviewList.builder()
                .reviewList(reviewList)
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .build();
    }

    // 7주차_미션2- 커서 기반 페이지네이션 변환
    public static MemberResDTO.MyPageReviewCursorList toReviewCursorList(
            Slice<Review> reviewSlice, String nextCursor
    ) {
        List<MemberResDTO.MyPageReview> reviewList = reviewSlice.getContent().stream()
                .map(MemberConverter::toReviewPreview)
                .toList();

        return MemberResDTO.MyPageReviewCursorList.builder()
                .reviewList(reviewList)
                .hasNext(reviewSlice.hasNext())
                .nextCursor(nextCursor)
                .pageSize(reviewSlice.getSize())
                .build();
    }
}