package com.example.umc10th.global.apipayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralErrorCode implements BaseErrorCode {

    // 400
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400_1", "유효하지 않은 요청값입니다."),
    VALID_FAIL(HttpStatus.BAD_REQUEST, "COMMON400_2", "유효성 검사에 실패했습니다."),

    // 401
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),

    // 403
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "접근 권한이 없습니다."),

    // 404
    NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON404", "요청한 리소스를 찾을 수 없습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER404", "존재하지 않는 사용자입니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404", "존재하지 않는 가게입니다."),
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404", "존재하지 않는 미션입니다."),

    // 409
    MISSION_ALREADY_COMPLETE(HttpStatus.CONFLICT, "MISSION409", "이미 완료된 미션입니다."),

    // 500
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 내부 오류입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}

