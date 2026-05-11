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
            String conditional,
            MissionStatus status
    ) {}

    // 미션 성공 누르기
    @Builder
    public record UpdateMissionStatusDTO(
            Long memberMissionId,
            MissionStatus status
    ) {}



    // 홈화면 - 도전 가능한 미션 목록
    @Builder
    public record HomeMissionItemDTO(
            Long missionId,
            String storeName,
            String category,
            Integer point,
            String conditional,
            Integer deadline
    ) {}

    @Builder
    public record HomeMissionListDTO(
            List<HomeMissionItemDTO> missions,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    // 가게 미션조회
    @Builder
    public record GetMission(
            Long missionId,
            Integer point,
            String conditional
    ){}

    // 페이지네이션 틀 — 제네릭으로 어떤 타입이든 담을 수 있음
    @Builder
    public record Pagination<T>(
            List<T> data,         // 실제 데이터
            Integer pageNumber,   // 현재 페이지 번호
            Integer pageSize      // 페이지 크기
    ){}
}