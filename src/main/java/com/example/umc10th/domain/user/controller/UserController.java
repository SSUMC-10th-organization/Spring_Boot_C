package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.mission.dto.MissionResponseDto;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.user.dto.UserRequestDto;
import com.example.umc10th.domain.user.dto.UserResponseDto;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.apipayload.code.GeneralSuccessCode;
import com.example.umc10th.global.apipayload.handler.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController implements UserControllerDocs {

    private final UserService userService;

    @GetMapping("/missions")
    public ResponseEntity<ApiResponse<MissionResponseDto.MissionListResult>> getMissions(
            @RequestHeader("X-User-Id") Long userId,
            @RequestParam(required = false) MissionStatus status,
            @RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(ApiResponse.onSuccess(GeneralSuccessCode.OK, userService.getMyMissions(userId, status, page)));
    }

    @PatchMapping("/mission/complete")
    public ResponseEntity<ApiResponse<UserResponseDto.CompleteMissionResult>> completeMission(
            @RequestHeader("X-User-Id") Long userId,
            @Valid @RequestBody UserRequestDto.CompleteMission request) {
        return ResponseEntity.ok(ApiResponse.onSuccess(GeneralSuccessCode.OK, userService.completeMission(userId, request)));
    }

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<UserResponseDto.MyProfile>> getMyProfile(
            @RequestHeader("X-User-Id") Long userId) {
        return ResponseEntity.ok(ApiResponse.onSuccess(GeneralSuccessCode.OK, userService.getMyProfile(userId)));
    }
}
