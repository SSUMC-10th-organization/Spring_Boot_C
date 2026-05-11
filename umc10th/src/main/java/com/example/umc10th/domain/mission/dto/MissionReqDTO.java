package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import lombok.Getter;

import java.time.LocalDate;

public class MissionReqDTO {

    // 미션 성공 누르기
    @Getter
    public static class UpdateMissionStatusDTO {
        MissionStatus status;
    }

    // 가게 미션 생성
    public record CreateMission(
            LocalDate deadline,
            Integer point,
            String conditional
    ){}


}