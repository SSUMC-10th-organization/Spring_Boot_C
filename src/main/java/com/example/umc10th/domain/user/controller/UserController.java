package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.mission.dto.MissionResponseDto;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.user.dto.UserRequestDto;
import com.example.umc10th.domain.user.dto.UserResponseDto;
import com.example.umc10th.domain.user.service.UserService;
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
        // TODO: 서비스 연동
        return null;
    }

    @PatchMapping("/mission/complete")
    public ResponseEntity<ApiResponse<UserResponseDto.CompleteMissionResult>> completeMission(
            @RequestHeader("X-User-Id") Long userId,
            @Valid @RequestBody UserRequestDto.CompleteMission request) {
        // TODO: 서비스 연동
        return null;
    }
}
