package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import lombok.Getter;

public class MissionReqDTO {

    @Getter
    public static class StatusUpdateDTO {
        private MissionStatus status;
    }

    public record GetMyMissions(
            Long memberId
    ) {}
}