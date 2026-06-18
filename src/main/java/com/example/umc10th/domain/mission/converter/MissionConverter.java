package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResponseDto;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.user.entity.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import java.util.List;

public class MissionConverter {

    public static MissionResponseDto.MissionItem toMissionItem(UserMission userMission) {
        Mission mission = userMission.getMission();
        return new MissionResponseDto.MissionItem(
                mission.getId(),
                mission.getStore().getName(),
                mission.getTitle(),
                mission.getDescription(),
                mission.getRewardPoints(),
                userMission.getStatus(),
                mission.getDeadlineDay()
        );
    }

    public static MissionResponseDto.MissionListResult toMissionListResult(Slice<UserMission> slice) {
        List<MissionResponseDto.MissionItem> items = slice.getContent().stream()
                .map(MissionConverter::toMissionItem)
                .toList();
        return new MissionResponseDto.MissionListResult(items, slice.hasNext());
    }

    public static MissionResponseDto.MyMissionListResult toMyMissionListResult(Page<UserMission> page) {
        List<MissionResponseDto.MissionItem> items = page.getContent().stream()
                .map(MissionConverter::toMissionItem)
                .toList();
        return new MissionResponseDto.MyMissionListResult(
                items,
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.hasNext()
        );
    }
}
