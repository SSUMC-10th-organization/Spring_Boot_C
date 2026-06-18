package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.mission.dto.MissionResponseDto;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.user.dto.AuthUser;
import com.example.umc10th.domain.user.dto.UserRequestDto;
import com.example.umc10th.domain.user.dto.UserResponseDto;
import com.example.umc10th.global.apipayload.handler.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "유저 관련 API")
@RequestMapping("/user")
public interface UserControllerDocs {

    @Operation(summary = "내 미션 목록 조회")
    @GetMapping("/missions")
    ResponseEntity<ApiResponse<MissionResponseDto.MissionListResult>> getMissions(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestParam(required = false) MissionStatus status,
            @RequestParam(defaultValue = "0") int page);

    @Operation(summary = "미션 완료 처리")
    @PatchMapping("/mission/complete")
    ResponseEntity<ApiResponse<UserResponseDto.CompleteMissionResult>> completeMission(
            @AuthenticationPrincipal AuthUser authUser,
            @Valid @RequestBody UserRequestDto.CompleteMission request);

    @Operation(summary = "내 프로필 조회")
    @GetMapping("/profile")
    ResponseEntity<ApiResponse<UserResponseDto.MyProfile>> getMyProfile(
            @AuthenticationPrincipal AuthUser authUser);
}
