package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_1", "해당 미션을 찾을 수 없습니다."),
    MISSION_ALREADY_COMPLETE(HttpStatus.BAD_REQUEST, "MISSION400_1", "이미 완료된 미션입니다."),
    MISSION_NOT_CHALLENGING(HttpStatus.BAD_REQUEST, "MISSION400_2", "진행중인 미션이 아닙니다."),
    INVALID_STATUS(HttpStatus.BAD_REQUEST, "MISSION400_3", "유효하지 않은 미션 상태입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}