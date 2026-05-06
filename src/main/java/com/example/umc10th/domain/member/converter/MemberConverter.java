package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;

public class MemberConverter {

    public static MemberResDTO.MyPageDTO toMyPageDTO(Member member) {
        return MemberResDTO.MyPageDTO.builder()
                .name(member.getName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .profileUrl(member.getProfileUrl())
                .point(member.getPoint())
                .build();
    }

    public static MemberResDTO.SignUpDTO toSignUpDTO (Member member) {
        return MemberResDTO.SignUpDTO.builder()
                .memberId(member.getId())
                .build();
    }
}
