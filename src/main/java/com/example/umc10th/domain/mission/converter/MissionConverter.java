package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import java.util.List;

public class MissionConverter {

    public static MissionResponseDTO.MissionDetailDTO toMissionDetailDTO(Long missionId) {
        return MissionResponseDTO.MissionDetailDTO.builder()
                .missionId(missionId)
                .title("샐러드 먹기")
                .description("오늘 한 끼를 샐러드로 먹기")
                .rewardPoint(500)
                .status("IN_PROGRESS")
                .build();
    }

    public static MissionResponseDTO.MissionListResultDTO toMissionListResultDTO(Integer page, Integer size) {
        return MissionResponseDTO.MissionListResultDTO.builder()
                .missions(List.of(
                        MissionResponseDTO.MissionPreviewDTO.builder().missionId(1L).title("샐러드 먹기").status("IN_PROGRESS").build(),
                        MissionResponseDTO.MissionPreviewDTO.builder().missionId(2L).title("물 2L 마시기").status("IN_PROGRESS").build()
                ))
                .page(page)
                .size(size)
                .build();
    }

    public static MissionResponseDTO.MissionSuccessResultDTO toMissionSuccessResultDTO(Long missionId, String memo) {
        return MissionResponseDTO.MissionSuccessResultDTO.builder()
                .missionId(missionId)
                .status("COMPLETED")
                .memo(memo)
                .build();
    }
}
