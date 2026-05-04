package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    // 회원가입
    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.SignUpDTO> signUp(
            @RequestBody MemberReqDTO.SignUpDTO request
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.SIGN_UP_SUCCESS, null);
    }
}