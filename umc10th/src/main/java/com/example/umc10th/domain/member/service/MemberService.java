package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.member.repository.MemberTermRepository;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.global.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 마이페이지 조회
//    public MemberResDTO.MyPageInfo getMyPage(Long memberId){
//
//        // DB에서 해당 유저 ID로 데이터 조회
//        Member member = memberRepository.findById(memberId)
//                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
//        // 컨버터를 이용해서 응답 DTO 생성 & return
//        return MemberConverter.toMyPageInfo(member);
//    }

    // authMember이용
    public MemberResDTO.MyPageInfo getMyPage(
            AuthMember member
    ){
        //컨버터를 통해 응답 DTO 생성 및 return
        return MemberConverter.toMyPageInfo(member.getMember());
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

    //  커서 기반 페이지네이션
    public MemberResDTO.MyPageReviewCursorList getMyReviewsByCursor(
            Long memberId, Integer pageSize, String cursor, String query
    ) {
        // 회원 검증
        if (!memberRepository.existsById(memberId)) {
            throw new MemberException(MemberErrorCode.MEMBER_NOT_FOUND);
        }

        // PageRequest 생성 (pageNumber는 0 고정)
        PageRequest pageRequest = PageRequest.of(0, pageSize);

        Slice<Review> reviewSlice;
        String nextCursor;

        // 커서가 있는 경우
        if (!cursor.equals("-1")) {
            String[] cursorSplit = cursor.split(":");

            switch (query.toLowerCase()) {
                case "id": {
                    // 커서 분리: "10:10" → idCursor = 10
                    Long idCursor = Long.parseLong(cursorSplit[1]);

                    reviewSlice = memberRepository
                            .findReviewsByMemberIdAndIdLessThanOrderByIdDesc(
                                    memberId, idCursor, pageRequest
                            );
                    break;
                }
                case "star": {
                    // 커서 분리: "4.5:733" → starCursor=4.5, idCursor=733
                    BigDecimal starCursor = new BigDecimal(cursorSplit[0]);
                    Long idCursor = Long.parseLong(cursorSplit[1]);

                    reviewSlice = memberRepository
                            .findReviewsByMemberIdAndStarCursor(
                                    memberId, starCursor, idCursor, pageRequest
                            );
                    break;
                }
                default:
                    throw new MemberException(MemberErrorCode.QUERY_NOT_VALID);
            }
        } else {
            // 첫 페이지 (커서 없음)
            switch (query.toLowerCase()) {
                case "id":
                    reviewSlice = memberRepository
                            .findReviewsByMemberIdOrderByIdDesc(memberId, pageRequest);
                    break;
                case "star":
                    reviewSlice = memberRepository
                            .findReviewsByMemberIdOrderByStarDesc(memberId, pageRequest);
                    break;
                default:
                    throw new MemberException(MemberErrorCode.QUERY_NOT_VALID);
            }
        }

        // 다음 커서 계산
        if (reviewSlice.getContent().isEmpty()) {
            nextCursor = "-1";
        } else {
            Review lastReview = reviewSlice.getContent().get(reviewSlice.getContent().size() - 1);
            switch (query.toLowerCase()) {
                case "id":
                    // ID 순일 때: "id:id" 형태
                    nextCursor = lastReview.getId() + ":" + lastReview.getId();
                    break;
                case "star":
                    // 별점 순일 때: "star:id" 형태
                    nextCursor = lastReview.getStar() + ":" + lastReview.getId();
                    break;
                default:
                    nextCursor = "-1";
            }
        }

        return MemberConverter.toReviewCursorList(reviewSlice, nextCursor);
    }
}