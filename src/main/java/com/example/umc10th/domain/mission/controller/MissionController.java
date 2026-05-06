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
@RequiredArgsConstructor // 추가: 서비스 주입
@RequestMapping("/api/v1/missions")
public class MissionController {

    private final MissionQueryService missionQueryService; // 추가: 아까 만든 서비스!

    @GetMapping
    public ApiResponse<MissionResponseDTO.MissionListResultDTO> getMissions(
            @RequestParam MissionStatus status, // String 대신 Enum으로 받으면 에러 방지에 좋습니다!
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        // 1. 서비스 호출: 아직 로그인이 없으므로 임시로 1번 유저(1L)라고 가정합니다.
        Page<UserMission> missionPage = missionQueryService.getMyMissions(1L, status, page);

        // (추후 과제) missionPage 객체의 데이터를 Converter를 통해 DTO로 바꾸는 작업이 필요합니다.
        // 현재는 기존 코드의 응답 포맷을 유지해둡니다!
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
