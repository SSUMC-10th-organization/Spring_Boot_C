package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.SocialType;
import com.example.umc10th.domain.mission.enums.Address;
import com.example.umc10th.global.security.dto.OAuthDTO;

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
    public static Member toMember(MemberReqDTO.SignUpDTO request, String encodedPassword) {
        return Member.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .name(request.getName())
                .gender(request.getGender())
                .birth(request.getBirthDate())
                .address(request.getAddress() != null ? Address.valueOf(request.getAddress()) : null)
                .point(0)
                .socialType(SocialType.LOCAL)
                .phoneNumber(request.getPhoneNumber())
                .build();
    }

    public static Member toOAuthMember(OAuthDTO dto) {
        return Member.builder()
                .email(dto.getSocialEmail())
                .name(dto.getName())
                .socialType(dto.getSocialType())
                .socialUid(dto.getSocialUid())
                .point(0)
                .build();
    }


}
