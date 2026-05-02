package com.example.umc10th.domain.store.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {

    GET_STORES_SUCCESS(HttpStatus.OK, "STORE200_1", "가게 목록 조회가 완료되었습니다."),
    GET_STORE_SUCCESS(HttpStatus.OK, "STORE200_2", "가게 상세 조회가 완료되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}