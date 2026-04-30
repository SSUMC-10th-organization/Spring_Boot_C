package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import lombok.Builder;
import java.util.List;

public class MissionResDTO {

    // 미션 목록 조회
    @Builder
    public record MissionListDTO(
            List<MissionItemDTO> missions
    ) {}

    @Builder
    public record MissionItemDTO(
            Long missionId,
            String storeName,
            Integer targetPoint,
            String missionSpec,
            MissionStatus status
    ) {}

    // 미션 성공 누르기
    @Builder
    public record UpdateMissionStatusDTO(
            Long memberMissionId,
            MissionStatus status
    ) {}
}