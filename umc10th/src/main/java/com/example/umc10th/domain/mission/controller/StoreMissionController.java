package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")   // ← 엔드포인트 분리
public class StoreMissionController {

    private final MissionService missionService;

    // 가게 미션 생성
    // POST /api/v1/stores/{storeId}/missions
    @PostMapping("/{storeId}/missions")
    public ApiResponse<Void> createMission(
            @PathVariable Long storeId,
            @RequestBody MissionReqDTO.CreateMission dto
    ){
        BaseSuccessCode code = MissionSuccessCode.CREATED;
        return ApiResponse.onSuccess(code, missionService.createMission(storeId, dto));
    }

    // 가게 내 미션들 조회
    // GET /api/v1/stores/{storeId}/missions
    @GetMapping("/{storeId}/missions")
    //public ApiResponse<List<MissionResDTO.GetMission>> getStoreMissions(
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.GetMission>> getStoreMissions(
            @PathVariable Long storeId,
            // 페이지네이션
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort   // 필수 아님
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMissions(storeId, pageSize, pageNumber, sort));
    }
}