package com.example.umc10th.global.apipayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK, "COMMON200", "요청에 성공하였습니다."),
    CREATED(HttpStatus.CREATED, "COMMON201", "생성에 성공하였습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
