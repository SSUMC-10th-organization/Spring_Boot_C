package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.store.entity.Store;
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
                .conditional(mm.getMission().getConditional())
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
                .conditional(m.getConditional())
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

    // DTO → 엔티티 변환 (가게 미션 생성)
    public static Mission toMission(
            Store store,
            MissionReqDTO.CreateMission dto
    ) {
        return Mission.builder()
                .store(store)
                .conditional(dto.conditional())
                .point(dto.point())
                .deadline(dto.deadline())
                .build();
    }

    // 엔티티 → 응답 DTO 변환 (미션 조회)
    public static MissionResDTO.GetMission toGetMission(Mission mission) {
        return MissionResDTO.GetMission.builder()
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .missionId(mission.getId())
                .build();
    }

    // 페이지네이션 변환 메서드
    // 1) 오프셋기반
//    public static <T> MissionResDTO.Pagination<T> toPagination(
//            List<T> data,
//            Integer pageNumber,
//            Integer pageSize
//    ) {
//        return MissionResDTO.Pagination.<T>builder()
//                .data(data)
//                .pageNumber(pageNumber)
//                .pageSize(pageSize)
//                .build();
//    }

    // 2) 커서기반
    // 페이지네이션 틀 생성 (커서용)
    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }
}

