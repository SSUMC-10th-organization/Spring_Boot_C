package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResponseDto;
import com.example.umc10th.domain.user.entity.UserMission;

import java.util.List;

public class MissionConverter {

    public static MissionResponseDto.MissionItem toMissionItem(UserMission userMission) {
        return new MissionResponseDto.MissionItem(
                userMission.getMission().getId(),
                userMission.getMission().getStore().getName(),
                userMission.getMission().getTitle(),
                userMission.getMission().getDescription(),
                userMission.getMission().getRewardPoints(),
                userMission.getMission().getDeadlineDay(),
                userMission.getStatus()
        );
    }

    public static MissionResponseDto.MissionListResult toMissionListResult(List<UserMission> userMissions) {
        List<MissionResponseDto.MissionItem> items = userMissions.stream()
                .map(MissionConverter::toMissionItem)
                .toList();
        return new MissionResponseDto.MissionListResult(items);
    }
}
