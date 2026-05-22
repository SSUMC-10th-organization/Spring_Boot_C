package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/me/missions")
public class MissionController {

    private final MissionService missionService;

    // 홈화면,홈 스크롤업화면 - 도전 가능한 미션 목록
    // GET /members/me/missions/home?locationId=1&page=0
    @GetMapping("/home")
    public ApiResponse<MissionResDTO.HomeMissionListDTO> getHomeMissions(
            @RequestHeader("Authorization") String authorization,
            @RequestParam Long locationId,
            @RequestParam(defaultValue = "0") int page
    ) {
        Long memberId = Long.parseLong(authorization); // 빠져있던 부분 추가
        return ApiResponse.onSuccess(
                MissionSuccessCode.GET_MISSIONS_SUCCESS,
                missionService.getHomeMissions(locationId, memberId, page)
        );
    }

    // 미션(홈)
    // 내 미션 목록 조회 (진행중 / 진행완료)
    // GET /members/me/missions?status=IN_PROGRESS
    // GET /members/me/missions?status=COMPLETED
    @GetMapping
    public ApiResponse<MissionResDTO.MissionListDTO> getMissions(
            //@RequestBody @Valid MissionReqDTO.GetMissions dto,
            @RequestParam Long memberId,
            @RequestParam MissionStatus status,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.GET_MISSIONS_SUCCESS,
                missionService.getMissions(memberId, status, pageSize, pageNumber)
        );
    }


    // 미션 성공 누르기
    // PATCH /members/me/missions/{memberMissionId}
    @PatchMapping("/{memberMissionId}")
    public ApiResponse<MissionResDTO.UpdateMissionStatusDTO> updateMissionStatus(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long memberMissionId,
            @RequestBody MissionReqDTO.UpdateMissionStatusDTO request
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.UPDATE_MISSION_SUCCESS,
                missionService.updateMissionStatus(memberMissionId, request.getStatus())
        );
    }
}