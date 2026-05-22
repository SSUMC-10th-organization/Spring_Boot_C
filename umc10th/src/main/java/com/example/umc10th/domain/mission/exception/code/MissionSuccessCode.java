package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    GET_MISSIONS_SUCCESS(HttpStatus.OK, "MISSION200_1", "미션 목록 조회가 완료되었습니다."),
    UPDATE_MISSION_SUCCESS(HttpStatus.OK, "MISSION200_2", "미션 상태 업데이트가 완료되었습니다."),
    CREATED(HttpStatus.OK, "MISSION200_1","성공적으로 미션을 생성했습니다."),
    OK(HttpStatus.OK, "MISSION200_2","성공적으로 미션을 조회했습니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}