package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionRequestDto;
import com.example.umc10th.domain.mission.dto.MissionResponseDto;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apipayload.code.GeneralSuccessCode;
import com.example.umc10th.global.apipayload.handler.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController implements MissionControllerDocs {

    private final MissionService missionService;

    @PostMapping("/my")
    public ResponseEntity<ApiResponse<MissionResponseDto.MyMissionListResult>> getMyMissions(
            @Valid @RequestBody MissionRequestDto.GetMyMissions request) {
        return ResponseEntity.ok(ApiResponse.onSuccess(GeneralSuccessCode.OK,
                missionService.getMyMissions(request.userId(), request.page())));
    }
}
