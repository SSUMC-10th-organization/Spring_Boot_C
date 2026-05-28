package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.UserRequestDTO;
import com.example.umc10th.domain.member.dto.UserResponseDTO;
import com.example.umc10th.domain.member.entity.User;

public class UserConverter {

    public static User toUser(UserRequestDTO.SignupDTO request, String encodedPassword) {
        return User.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .name(request.getName())
                .gender(request.getGender())
                .birthDate(request.getBirthDate())
                .address(request.getAddress())
                .currentPoint(0)
                .build();
    }

    public static UserResponseDTO.SignupResultDTO toSignupResultDTO(Long userId, String email, String name) {
        return UserResponseDTO.SignupResultDTO.builder()
                .userId(userId)
                .email(email)
                .name(name)
                .build();
    }

    public static UserResponseDTO.LoginResultDTO toLoginResultDTO(String accessToken) {
        return UserResponseDTO.LoginResultDTO.builder()
                .accessToken(accessToken)
                .build();
    }

    public static UserResponseDTO.MyPageResultDTO toMyPageResultDTO(User user) {
        return UserResponseDTO.MyPageResultDTO.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .gender(user.getGender() != null ? user.getGender().name() : null)
                .birthDate(user.getBirthDate() != null ? user.getBirthDate().toString() : null)
                .address(user.getAddress())
                .currentPoint(user.getCurrentPoint())
                .build();
    }

    public static UserResponseDTO.HomeResultDTO toHomeResultDTO() {
        return UserResponseDTO.HomeResultDTO.builder()
                .title("UMC Home")
                .message("홈 조회 성공")
                .build();
    }
}
