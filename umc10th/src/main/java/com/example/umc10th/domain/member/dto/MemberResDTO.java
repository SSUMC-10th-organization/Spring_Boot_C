package com.example.umc10th.domain.member.dto;

import lombok.Builder;

public class MemberResDTO {

    @Builder
    public record SignUpDTO(
            Long memberId
    ) {}
}