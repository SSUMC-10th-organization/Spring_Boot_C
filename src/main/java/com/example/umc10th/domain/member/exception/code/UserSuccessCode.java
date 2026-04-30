package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserSuccessCode implements BaseSuccessCode {

    MEMBER_JOIN_SUCCESS(HttpStatus.OK, "MEMBER200_1", "회원가입에 성공했습니다."),
    MEMBER_LOGIN_SUCCESS(HttpStatus.OK, "MEMBER200_2", "로그인에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
