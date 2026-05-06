package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;

import java.time.LocalDate;

public class MissionConverter {
    // Mission → MissionDTO
    public static MissionResDTO.MissionDTO toMissionDTO(Mission mission) {
        return MissionResDTO.MissionDTO.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .missionContent(mission.getMissionContent())
                .reward(mission.getPoint())
                .dDay((int) (mission.getDeadline().toEpochDay() -
                        LocalDate.now().toEpochDay()))
                .build();
    }

    // MemberMission → MissionDTO
    public static MissionResDTO.MissionDTO toMyMissionDTO(MemberMission memberMission) {
        return MissionResDTO.MissionDTO.builder()
                .missionId(memberMission.getMission().getId())
                .storeName(memberMission.getMission().getStore().getName())
                .missionContent(memberMission.getMission().getMissionContent())
                .reward(memberMission.getMission().getPoint())
                .status(memberMission.getIsComplete() ? "COMPLETE" : "CHALLENGING")
                .dDay((int) (memberMission.getMission().getDeadline().toEpochDay() -
                        LocalDate.now().toEpochDay()))
                .build();
    }
}
