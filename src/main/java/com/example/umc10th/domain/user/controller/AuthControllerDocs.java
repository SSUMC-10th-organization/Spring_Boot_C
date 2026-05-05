package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.UserRequestDto;
import com.example.umc10th.domain.user.dto.UserResponseDto;
import com.example.umc10th.global.apipayload.handler.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Auth", description = "인증 관련 API")
@RequestMapping("/auth")
public interface AuthControllerDocs {

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    ResponseEntity<ApiResponse<UserResponseDto.SignUpResult>> signUp(
            @Valid @RequestBody UserRequestDto.SignUp request);
}
