package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.converter.UserConverter;
import com.example.umc10th.domain.member.dto.UserRequestDTO;
import com.example.umc10th.domain.member.dto.UserResponseDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @PostMapping("/signup")
    public ApiResponse<UserResponseDTO.SignupResultDTO> signup(
            @RequestBody UserRequestDTO.SignupDTO request
    ) {
        // 길었던 Builder 로직이 Converter 호출 한 줄로 끝
        return ApiResponse.onSuccess(
                UserConverter.toSignupResultDTO(1L, request.getEmail(), request.getName())
        );
    }
}
