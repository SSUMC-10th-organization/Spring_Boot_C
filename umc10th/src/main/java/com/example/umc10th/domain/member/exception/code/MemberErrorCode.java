package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "해당 사용자를 찾을 수 없습니다."),
    MEMBER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "MEMBER400_1", "이미 존재하는 회원입니다."),
    QUERY_NOT_VALID(HttpStatus.BAD_REQUEST,"MEMBER400_2","유효하지 않은 query 값입니다."),
    PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST, "MEMBER400_3", "비밀번호가 일치하지 않습니다.");   // 알맞은 주소형식이 아닐 때 에러가 있을수도
    private final HttpStatus status;
    private final String code;
    private final String message;
}