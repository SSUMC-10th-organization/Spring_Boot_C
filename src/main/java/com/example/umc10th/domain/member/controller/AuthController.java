package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.UserRequestDTO;
import com.example.umc10th.domain.member.dto.UserResponseDTO;
import com.example.umc10th.domain.member.service.UserService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth", description = "회원가입 / 로그인 API")
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입")
    public ApiResponse<UserResponseDTO.SignupResultDTO> signup(
            @RequestBody UserRequestDTO.SignupDTO request
    ) {
        UserResponseDTO.SignupResultDTO result = userService.joinUser(request);
        return ApiResponse.onSuccess(result);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인")
    public ApiResponse<UserResponseDTO.LoginResultDTO> login(
            @RequestBody UserRequestDTO.LoginDTO request
    ) {
        UserResponseDTO.LoginResultDTO result = userService.login(request);
        return ApiResponse.onSuccess(result);
    }
}
