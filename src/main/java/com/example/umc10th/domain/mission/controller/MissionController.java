package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionRequestDTO;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/missions")
public class MissionController {

    @GetMapping
    public ApiResponse<MissionResponseDTO.MissionListResultDTO> getMissions(
            @RequestParam String status,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        MissionResponseDTO.MissionListResultDTO result = MissionResponseDTO.MissionListResultDTO.builder()
                .missions(List.of(
                        MissionResponseDTO.MissionPreviewDTO.builder()
                                .missionId(1L)
                                .title("샐러드 먹기")
                                .status(status)
                                .build(),
                        MissionResponseDTO.MissionPreviewDTO.builder()
                                .missionId(2L)
                                .title("물 2L 마시기")
                                .status(status)
                                .build()
                ))
                .page(page)
                .size(size)
                .build();

        return ApiResponse.onSuccess(result);
    }

    @GetMapping("/{missionId}")
    public ApiResponse<MissionResponseDTO.MissionDetailDTO> getMissionDetail(
            @PathVariable Long missionId
    ) {
        MissionResponseDTO.MissionDetailDTO result = MissionResponseDTO.MissionDetailDTO.builder()
                .missionId(missionId)
                .title("샐러드 먹기")
                .description("오늘 한 끼를 샐러드로 먹기")
                .rewardPoint(500)
                .status("IN_PROGRESS")
                .build();

        return ApiResponse.onSuccess(result);
    }

    @PatchMapping("/{missionId}/success")
    public ApiResponse<MissionResponseDTO.MissionSuccessResultDTO> successMission(
            @PathVariable Long missionId,
            @RequestBody MissionRequestDTO.MissionSuccessDTO request
    ) {
        MissionResponseDTO.MissionSuccessResultDTO result = MissionResponseDTO.MissionSuccessResultDTO.builder()
                .missionId(missionId)
                .status("COMPLETED")
                .memo(request.getMemo())
                .build();

        return ApiResponse.onSuccess(result);
    }
}
