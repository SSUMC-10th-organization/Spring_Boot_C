package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;

    // 1. 홈화면 미션 목록 조회
    @GetMapping
    public ApiResponse<Page<MissionResDTO.MissionDTO>> getMissions(
            @RequestParam Long regionId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.GET_MISSIONS_SUCCESS,
                missionService.getMissions(regionId, page, size));
    }

    // 2. 내 미션 목록 조회 (진행중/완료)
    @GetMapping("/me")
    public ApiResponse<Page<MissionResDTO.MissionDTO>> getMyMissions(
            @RequestParam Long memberId,
            @RequestParam String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.GET_MY_MISSIONS_SUCCESS,
                missionService.getMyMissions(memberId, status, page, size));
    }

    // 3. 미션 성공 요청
    @PatchMapping("/{missionId}/status")
    public ApiResponse<MissionResDTO.StatusUpdateDTO> updateMissionStatus(
            @PathVariable Long missionId,
            @RequestBody @Valid MissionReqDTO.StatusUpdateDTO request
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.UPDATE_MISSION_STATUS_SUCCESS, null);
    }
}
