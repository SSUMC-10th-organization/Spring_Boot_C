package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.MissionStatus;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResponseDto {

    public record Result() {
    }

    public record MissionItem(
            Long missionId,
            String storeName,
            String missionTitle,
            String description,
            Integer rewardPoints,
            MissionStatus status,
            LocalDateTime deadline
    ) {
    }

    public record MissionListResult(
            List<MissionItem> missionList,
            Boolean hasNext
    ) {
    }

    public record MyMissionListResult(
            List<MissionItem> missionList,
            int currentPage,
            int totalPages,
            long totalElements,
            Boolean hasNext
    ) {
    }
}
