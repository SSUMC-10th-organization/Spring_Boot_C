package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionRequestDTO;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/missions")
public class MissionController {

    @GetMapping
    public ApiResponse<MissionResponseDTO.MissionListResultDTO> getMissions(
            @RequestParam String status,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        return ApiResponse.onSuccess(MissionConverter.toMissionListResultDTO(page, size));
    }

    @GetMapping("/{missionId}")
    public ApiResponse<MissionResponseDTO.MissionDetailDTO> getMissionDetail(
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(MissionConverter.toMissionDetailDTO(missionId));
    }

    @PatchMapping("/{missionId}/success")
    public ApiResponse<MissionResponseDTO.MissionSuccessResultDTO> successMission(
            @PathVariable Long missionId,
            @RequestBody MissionRequestDTO.MissionSuccessDTO request
    ) {
        return ApiResponse.onSuccess(
                MissionConverter.toMissionSuccessResultDTO(missionId, request.getMemo())
        );
    }
}
