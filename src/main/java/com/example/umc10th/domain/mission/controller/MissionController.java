package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionRequestDTO;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.service.MissionQueryService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/missions")
public class MissionController {

    private final MissionQueryService missionQueryService;

    // 7주차 [미션 1번] 오프셋 기반 페이징 + RequestBody로 userId 받기 (요구사항)
    @GetMapping("/users/in-progress")
    public ApiResponse<MissionResponseDTO.UserMissionListDTO> getInprogressMissions(
            @RequestBody MissionRequestDTO.GetInprogressMissionReq request,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size) {

        Page<UserMission> userMissionPage = missionQueryService.getInprogressMissions(request.getUserId(), page, size);
        return ApiResponse.onSuccess(MissionConverter.toUserMissionListDTO(userMissionPage));
    }

    // 아래는 전부 기존 코드 유지
    @GetMapping
    public ApiResponse<MissionResponseDTO.MissionListResultDTO> getMissions(
            @RequestParam MissionStatus status,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        Page<UserMission> missionPage = missionQueryService.getMyMissions(1L, status, page);
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
