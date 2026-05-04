package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/me/missions")
public class MissionController {

    // 미션 목록 조회 (진행중 / 진행완료)
    // GET /members/me/missions?status=IN_PROGRESS
    // GET /members/me/missions?status=COMPLETED
    @GetMapping
    public ApiResponse<MissionResDTO.MissionListDTO> getMissions(
            @RequestHeader("Authorization") String authorization,
            @RequestParam MissionStatus status
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.GET_MISSIONS_SUCCESS, null);
    }

    // 미션 성공 누르기
    // PATCH /members/me/missions/{memberMissionId}
    @PatchMapping("/{memberMissionId}")
    public ApiResponse<MissionResDTO.UpdateMissionStatusDTO> updateMissionStatus(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long memberMissionId,
            @RequestBody MissionReqDTO.UpdateMissionStatusDTO request
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.UPDATE_MISSION_SUCCESS, null);
    }
}