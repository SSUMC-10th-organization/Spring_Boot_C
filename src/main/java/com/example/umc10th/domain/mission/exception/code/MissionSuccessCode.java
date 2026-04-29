package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    GET_MISSIONS_SUCCESS(HttpStatus.OK, "MISSION200_1", "미션 목록 조회가 완료되었습니다."),
    GET_MY_MISSIONS_SUCCESS(HttpStatus.OK, "MISSION200_2", "내 미션 목록 조회가 완료되었습니다."),
    UPDATE_MISSION_STATUS_SUCCESS(HttpStatus.OK, "MISSION200_3", "미션 성공 요청이 완료되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}