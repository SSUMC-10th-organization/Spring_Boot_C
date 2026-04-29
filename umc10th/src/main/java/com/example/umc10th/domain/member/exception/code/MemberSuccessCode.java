package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    JOIN_OK(HttpStatus.OK, "MEMBER200_1", "회원가입이 완료되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}