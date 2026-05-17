package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.exception.StoreException;
import com.example.umc10th.domain.store.exception.code.StoreErrorCode;
import com.example.umc10th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    // 리뷰 작성
    @Transactional
    public ReviewResDTO.CreateReviewDTO createReview(
            Long memberId,
            Long storeId,
            ReviewReqDTO.CreateReviewDTO request
    ) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Review review = Review.builder()
                .content(request.getReviewContent())
                .star(request.getScore())
                .member(member)
                .store(store)
                .build();

        Review savedReview = reviewRepository.save(review);

        return ReviewConverter.toCreateReviewDTO(savedReview);
    }


    @Transactional(readOnly = true)
    public ReviewResDTO.Pagination<ReviewResDTO.CreateReviewDTO> getMyReviews(
            Long memberId, Integer pageSize, String cursor, String query) {

        // 페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(0, pageSize);

        long idCursor;
        Slice<Review> reviewList;
        String nextCursor;

        // 커서가 있는 경우
        if (!cursor.equals("-1")) {

            // 커서 분리
            String[] cursorSplit = cursor.split(":");
            switch (query.toLowerCase()) {
                case "id":
                    // 커서 타입 변환
                    Long prevCursor = Long.parseLong(cursorSplit[0]);
                    idCursor = Long.parseLong(cursorSplit[1]);

                    // 조회 & where절에 커서값 기입
                    reviewList = reviewRepository.findByMemberIdAndIdCursor(memberId, idCursor, pageRequest);
                    break;
                case "star":
                    BigDecimal star = new BigDecimal(cursorSplit[0]);
                    idCursor = Long.parseLong(cursorSplit[1]);
                    reviewList = reviewRepository.findByMemberIdAndStarCursor(memberId, star, idCursor, pageRequest);
                    break;
                default:
                    throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
            }
        } else {
            // 커서 없이 조회
            if (query != null && query.toLowerCase().equals("star")) {
                reviewList = reviewRepository.findByMemberIdOrderByStar(memberId, pageRequest);
            } else {
                reviewList = reviewRepository.findByMemberId(memberId, pageRequest);
            }
        }

        // 다음 커서 계산
        if (reviewList.getContent().isEmpty() || !reviewList.hasNext()) {
            nextCursor = null;
        } else if (query != null && query.toLowerCase().equals("star")) {
            nextCursor = reviewList.getContent().getLast().getStar() + ":"
                    + reviewList.getContent().getLast().getId();
        } else {
            nextCursor = reviewList.getContent().getLast().getId() + ":"
                    + reviewList.getContent().getLast().getId();
        }

        return ReviewConverter.toPagination(
                reviewList.map(ReviewConverter::toCreateReviewDTO).toList(),
                reviewList.hasNext(),
                nextCursor,
                reviewList.getSize()
        );
    }
}