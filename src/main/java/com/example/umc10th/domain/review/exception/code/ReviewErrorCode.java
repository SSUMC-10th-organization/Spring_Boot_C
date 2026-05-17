package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_1", "해당 리뷰를 찾을 수 없습니다."),
    REVIEW_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "REVIEW400_1", "이미 작성한 리뷰가 있습니다."),
    QUERY_NOT_VALID(HttpStatus.BAD_REQUEST, "REVIEW400_2", "유효하지 않은 쿼리입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}