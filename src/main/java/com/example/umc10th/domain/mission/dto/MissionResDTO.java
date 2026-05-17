package com.example.umc10th.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class MissionResDTO {

    // 홈화면 미션 목록, 미션 목록 조회 응답
    @Builder
    public record MissionDTO(
            Long missionId,
            String storeName,
            String missionContent,
            Integer reward,
            String status,
            Integer dDay
    ) {}



    // 미션 상태 변경 응답
    @Builder
    public record StatusUpdateDTO(
            Long missionId,
            String status
    ) {}

    //페이지네이션 dto
    @Builder
    @Getter
    public static class Pagination<T> {
        private List<T> data;
        private Integer pageNumber;
        private Integer pageSize;
        private Long totalElements;
    }
}