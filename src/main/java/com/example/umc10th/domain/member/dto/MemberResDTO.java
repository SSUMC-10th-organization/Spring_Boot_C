package com.example.umc10th.domain.member.dto;

import lombok.Builder;

public class MemberResDTO {
    //회원가입
    @Builder
    public record SignUpDTO(
            Long memberId
    ) {}
    //마이페이지 조회
    @Builder
    public record MyPageDTO(
            String name,
            String email,
            String phoneNumber,
            String profileUrl,
            Integer point
    ) {}

    @Builder
    public record LoginDTO(
            String accessToken
    ) {}

}