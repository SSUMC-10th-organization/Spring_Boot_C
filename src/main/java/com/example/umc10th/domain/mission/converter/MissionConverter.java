package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

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
                .status(memberMission.getStatus().name())
                .dDay((int) (memberMission.getMission().getDeadline().toEpochDay() -
                        LocalDate.now().toEpochDay()))
                .build();
    }

    public static <T> MissionResDTO.Pagination<T> toPagination(Page<T> page) {
        return MissionResDTO.Pagination.<T>builder()
                .data(page.getContent())
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .build();
    }
}
