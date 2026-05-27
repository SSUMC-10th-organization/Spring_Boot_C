package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.UserRequestDTO;
import com.example.umc10th.domain.member.dto.UserResponseDTO;
import com.example.umc10th.domain.member.service.UserService; // 추가
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor; // 추가
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor // Service를 주입받기 위해 꼭 필요함!
@RequestMapping("/api/v1/auth")
public class AuthController {

    // 방금 만든 UserService를 가져옴
    private final UserService userService;

    @PostMapping("/signup")
    public ApiResponse<UserResponseDTO.SignupResultDTO> signup(
            @RequestBody UserRequestDTO.SignupDTO request
    ) {
        // 가짜 데이터를 넘기던 부분을 지우고, Service의 joinUser 메서드 호출!
        UserResponseDTO.SignupResultDTO result = userService.joinUser(request);

        return ApiResponse.onSuccess(result);
    }
}
