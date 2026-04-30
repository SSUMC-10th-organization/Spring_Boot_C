package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import lombok.Getter;

public class MissionReqDTO {

    // 미션 성공 누르기
    @Getter
    public static class UpdateMissionStatusDTO {
        MissionStatus status;  // String → enum
    }




}