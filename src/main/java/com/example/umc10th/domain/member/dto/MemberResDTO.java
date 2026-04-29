package com.example.umc10th.domain.member.dto;

import lombok.Builder;

public class MemberResDTO {
    //회원가입
    @Builder
    public record SignUpDTO(
            Long memberId
    ) {}

}