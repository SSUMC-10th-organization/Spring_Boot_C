package com.example.umc10th.global.apiPayload;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
// ↑ JSON으로 변환할 때 이 순서로 출력
public class ApiResponse<T> {
//                      ↑ T = 어떤 타입이든 올 수 있음 (제네릭)

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;  // 성공 여부

    @JsonProperty("code")
    private final String code;        // 응답 코드

    @JsonProperty("message")
    private final String message;     // 응답 메시지

    @JsonProperty("result")
    private T result;                 // 실제 데이터 (성공시 데이터, 실패시 에러정보)

    // 실패한 경우 ApiResponse 만들기
    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result) {
        return new ApiResponse<>(
                false,              // isSuccess = false
                code.getCode(),     // 에러코드에서 code 꺼냄
                code.getMessage(),  // 에러코드에서 message 꺼냄
                result              // 에러 상세 정보
        );
    }
}