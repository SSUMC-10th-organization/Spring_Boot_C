package com.example.umc10th.global.apiPayload;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;  // 성공 여부

    @JsonProperty("code")
    private final String code;        // 응답 코드

    @JsonProperty("message")
    private final String message;     // 응답 메시지

    @JsonProperty("result")
    private T result;                 // 실제 데이터 (성공시 데이터, 실패시 에러정보)


    public ApiResponse(Boolean isSuccess, String code, String message, T result) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
        this.result=result;
    }

    // 성공한 경우
    public static <T> ApiResponse<T>        onSuccess(BaseSuccessCode code, T result){
        return new ApiResponse<>(
                true,
                code.getCode(),
                code.getMessage(),
                result
        );
    }

    // 실패한 경우 ApiResponse 만들기, 상태코드 나타낼때 code, 실패결과가 있다면 담을 result
    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result) {
        return new ApiResponse<>(
                false,              // isSuccess = false
                code.getCode(),     // 에러코드에서 code 꺼냄
                code.getMessage(),  // 에러코드에서 message 꺼냄
                result         // 에러 상세 정보
        );
    }


}