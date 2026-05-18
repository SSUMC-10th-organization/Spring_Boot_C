package com.example.umc10th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ReviewResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewCreateResultDTO {
        private Long reviewId;
        private Long missionId;
        private Integer rating;
        private String content;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreviewDTO {
        private Long reviewId;
        private Integer rating;
        private String content;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyReviewListDTO {
        private List<ReviewPreviewDTO> reviews;
        private Integer page;
        private Integer size;
    }

    // 커서 페이징 응답용 DTO (화면 참고하여 닉네임, 생성일 추가 / 사진 제외)
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyReviewCursorDTO {
        private Long reviewId;
        private String storeName;
        private String nickname;
        private Integer rating;
        private String content;
        private String createdAt;
        // 7주차 미션 2 - 사장님 답글 추가
        private String ownerReply;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyReviewCursorListDTO {
        private List<MyReviewCursorDTO> reviews;
        private Integer listSize;
        private Boolean hasNext;
        private Long nextCursorId;
        private Integer nextCursorRating;
    }
}
