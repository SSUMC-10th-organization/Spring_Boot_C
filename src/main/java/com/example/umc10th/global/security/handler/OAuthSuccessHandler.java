package com.example.umc10th.global.security.handler;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.security.entity.AuthMember;
import com.example.umc10th.global.security.entity.OAuthMember;
import com.example.umc10th.global.security.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@RequiredArgsConstructor
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {
        // 응답 설정
        MemberSuccessCode code = MemberSuccessCode.LOGIN_SUCCESS;
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(code.getStatus().value());

        // SecurityContextHolder에서 OAuthMember 꺼내기
        OAuthMember member = (OAuthMember) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        // JWT 토큰 생성
        String accessToken = jwtUtil.createAccessToken(new AuthMember(member.getMember()));

        // 응답 반환
        ApiResponse<MemberResDTO.LoginDTO> responseBody = ApiResponse.onSuccess(
                code,
                MemberResDTO.LoginDTO.builder()
                        .accessToken(accessToken)
                        .build()
        );

        objectMapper.writeValue(response.getOutputStream(), responseBody);
    }
}