package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ReviewService {

    // store, member의 존재 확인을 해야한다.
    private final ReviewRepository reviewRepository; //storeRepository → "이 storeId의 가게가 실제로 존재하는지" 확인
    private final StoreRepository storeRepository; // memberRepository → "이 memberId의 회원이 실제로 존재하는지" 확인
    private final MemberRepository memberRepository; // reviewRepository → 최종적으로 리뷰를 저장


    //DB 작업 중 에러가 나면 전체를 되돌려주는(rollback) 역할
    @Transactional
    public ReviewResDTO.CreateReviewDTO createReview(Long storeId, Long memberId, ReviewReqDTO.CreateReviewDTO request) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));

        // 컨버터에게 위임
        Review review = ReviewConverter.toReview(request, store, member);
        Review saved = reviewRepository.save(review);

        return ReviewConverter.toCreateReviewDTO(saved);
    }
}