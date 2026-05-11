package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.review.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 마이페이지 조회
    public MemberResDTO.MyPageInfo getMyPage(Long memberId){

        // DB에서 해당 유저 ID로 데이터 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        // 컨버터를 이용해서 응답 DTO 생성 & return
        return MemberConverter.toMyPageInfo(member);
    }


    // 작성한 리뷰 페이징 조회
    public MemberResDTO.MyPageReviewList getMyReviews(Long memberId, Integer page, Integer size) {
        // 회원 존재 검증
        if (!memberRepository.existsById(memberId)) {
            throw new MemberException(MemberErrorCode.MEMBER_NOT_FOUND);
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<Review> reviewPage = memberRepository.findReviewsByMemberId(memberId, pageable);

        return MemberConverter.toReviewList(reviewPage);
    }
}