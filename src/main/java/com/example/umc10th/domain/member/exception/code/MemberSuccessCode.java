package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    SIGN_UP_SUCCESS(HttpStatus.OK, "MEMBER200_1", "회원가입이 완료되었습니다."),
    GET_MY_PAGE_SUCCESS(HttpStatus.OK, "MEMBER200_2", "마이페이지 조회가 완료되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}