package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.UserResponseDTO;
import java.util.List;

public class UserConverter {

    public static UserResponseDTO.SignupResultDTO toSignupResultDTO(Long memberId, String email, String name) {
        return UserResponseDTO.SignupResultDTO.builder()
                .memberId(memberId)
                .email(email)
                .name(name)
                .build();
    }

    public static UserResponseDTO.HomeResultDTO toHomeResultDTO() {
        return UserResponseDTO.HomeResultDTO.builder()
                .nickname("홍길동")
                .point(2500)
                .ongoingMissions(List.of("샐러드 먹기", "물 2L 마시기"))
                .build();
    }
}
