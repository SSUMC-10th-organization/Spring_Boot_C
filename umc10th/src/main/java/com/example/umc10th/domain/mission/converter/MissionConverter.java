package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class MissionConverter {

    // 1. 진행 중/ 진행완료 미션 모아보는 화면
    // MemberMission 한 건 → MissionItemDTO
    public static MissionResDTO.MissionItemDTO toMissionItemDTO(MemberMission mm) {
        return MissionResDTO.MissionItemDTO.builder()
                .missionId(mm.getMission().getId())
                .storeName(mm.getMission().getStore().getName())
                .targetPoint(mm.getMission().getPoint())
                .condition(mm.getMission().getCondition())
                .status(mm.getIsCompleted())
                .build();
    }
    // Page<MemberMission> → MissionListDTO
    public static MissionResDTO.MissionListDTO toMissionListDTO(Page<MemberMission> page) {
        List<MissionResDTO.MissionItemDTO> missions = page.getContent().stream()
                .map(MissionConverter::toMissionItemDTO)  // 위 메서드 재사용
                .toList();
        // missions List를 응답DTO로 포장
        return MissionResDTO.MissionListDTO.builder()
                .missions(missions)
                .build();
    }


    // 홈화면
    // 홈화면 추가 - Mission 한 건 → HomeMissionItemDTO
    public static MissionResDTO.HomeMissionItemDTO toHomeMissionItemDTO(Mission m) {
        return MissionResDTO.HomeMissionItemDTO.builder()
                .missionId(m.getId())
                .storeName(m.getStore().getName())
                .point(m.getPoint())
                .condition(m.getCondition())
                .deadline((int) ChronoUnit.DAYS.between(LocalDate.now(), m.getDeadline()))
                .build();
    }

    // 홈화면 추가 - Page<Mission> → HomeMissionListDTO
    public static MissionResDTO.HomeMissionListDTO toHomeMissionListDTO(Page<Mission> page) {
        List<MissionResDTO.HomeMissionItemDTO> missions = page.getContent().stream()
                .map(MissionConverter::toHomeMissionItemDTO) // 위 메서드 재사용
                .toList();

        return MissionResDTO.HomeMissionListDTO.builder()
                .missions(missions)
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}

