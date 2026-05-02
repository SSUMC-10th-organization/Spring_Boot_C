package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    // 1. 홈화면 미션 목록 조회
    @GetMapping
    public ApiResponse<List<MissionResDTO.MissionDTO>> getMissions(
            @RequestParam Long regionId
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.GET_MISSIONS_SUCCESS, null);
    }

    // 2. 내 미션 목록 조회 (진행중/완료)
    @GetMapping("/me")
    public ApiResponse<List<MissionResDTO.MissionDTO>> getMyMissions(
            @RequestParam String status
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.GET_MY_MISSIONS_SUCCESS, null);
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
