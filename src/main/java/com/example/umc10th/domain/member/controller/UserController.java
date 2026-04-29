package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.UserRequestDTO;
import com.example.umc10th.domain.member.dto.UserResponseDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @PostMapping("/auth/signup")
    public ApiResponse<UserResponseDTO.SignupResultDTO> signup(
            @RequestBody UserRequestDTO.SignupDTO request
    ) {
        UserResponseDTO.SignupResultDTO result = UserResponseDTO.SignupResultDTO.builder()
                .memberId(1L)
                .email(request.getEmail())
                .name(request.getName())
                .build();

        return ApiResponse.onSuccess(result);
    }

    @GetMapping("/home")
    public ApiResponse<UserResponseDTO.HomeResultDTO> getHome() {
        UserResponseDTO.HomeResultDTO result = UserResponseDTO.HomeResultDTO.builder()
                .nickname("홍길동")
                .point(2500)
                .ongoingMissions(List.of("샐러드 먹기", "물 2L 마시기"))
                .build();

        return ApiResponse.onSuccess(result);
    }
}
