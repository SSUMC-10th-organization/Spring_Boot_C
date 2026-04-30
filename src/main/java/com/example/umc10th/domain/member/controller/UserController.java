package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.converter.UserConverter;
import com.example.umc10th.domain.member.dto.UserResponseDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @GetMapping("/home")
    public ApiResponse<UserResponseDTO.HomeResultDTO> getHome() {
        // 데이터 생성 책임을 Converter로 넘김
        return ApiResponse.onSuccess(UserConverter.toHomeResultDTO());
    }
}
