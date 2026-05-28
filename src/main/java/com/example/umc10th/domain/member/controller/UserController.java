package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.converter.UserConverter;
import com.example.umc10th.domain.member.dto.UserResponseDTO;
import com.example.umc10th.domain.member.entity.User;
import com.example.umc10th.domain.member.service.UserService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "User", description = "유저 API")
public class UserController {

    private final UserService userService;

    @GetMapping("/home")
    @Operation(summary = "홈 조회")
    public ApiResponse<UserResponseDTO.HomeResultDTO> getHome() {
        return ApiResponse.onSuccess(UserConverter.toHomeResultDTO());
    }

    @GetMapping("/users/me")
    @Operation(summary = "마이페이지 조회", description = "JWT 토큰 기반으로 현재 로그인한 사용자 정보를 조회합니다.")
    @SecurityRequirement(name = "bearerAuth")
    public ApiResponse<UserResponseDTO.MyPageResultDTO> getMyPage(
            @AuthenticationPrincipal User user
    ) {
        return ApiResponse.onSuccess(userService.getMyPage(user));
    }
}
