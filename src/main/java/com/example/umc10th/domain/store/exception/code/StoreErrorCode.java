package com.example.umc10th.domain.store.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {

    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404_1", "해당 가게를 찾을 수 없습니다."),
    STORE_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "STORE400_1", "이미 존재하는 가게입니다."),
    INVALID_STORE_INFO(HttpStatus.BAD_REQUEST, "STORE400_2", "잘못된 가게 정보입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}