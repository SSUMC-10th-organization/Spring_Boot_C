package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionRequestDto;
import com.example.umc10th.domain.mission.dto.MissionResponseDto;
import com.example.umc10th.global.apipayload.handler.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Mission", description = "미션 관련 API")
@RequestMapping("/api/missions")
public interface MissionControllerDocs {

    @Operation(summary = "진행중인 미션 목록 조회 (오프셋 기반 페이지네이션)")
    @PostMapping("/my")
    ResponseEntity<ApiResponse<MissionResponseDto.MyMissionListResult>> getMyMissions(
            @Valid @RequestBody MissionRequestDto.GetMyMissions request);
}
