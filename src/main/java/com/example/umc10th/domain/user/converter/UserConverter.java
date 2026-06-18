package com.example.umc10th.domain.user.converter;

import com.example.umc10th.domain.user.dto.UserRequestDto;
import com.example.umc10th.domain.user.dto.UserResponseDto;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.entity.UserMission;

import java.util.List;

public class UserConverter {

    public static User toUser(UserRequestDto.SignUp request, String encodedPassword) {
        return User.builder()
                .email(request.email())
                .password(encodedPassword)
                .name(request.name())
                .gender(request.gender())
                .birthday(request.birthDate())
                .address(request.address())
                .point(0)
                .build();
    }

    public static UserResponseDto.SignUpResult toSignUpResult(User user) {
        List<String> foodPreferences = user.getUserCategories().stream()
                .map(uc -> uc.getCategory().getName().name())
                .toList();
        return new UserResponseDto.SignUpResult(
                user.getId(),
                user.getName(),
                user.getGender(),
                user.getBirthday(),
                foodPreferences,
                user.getCreatedAt()
        );
    }

    public static UserResponseDto.CompleteMissionResult toCompleteMissionResult(UserMission userMission) {
        return new UserResponseDto.CompleteMissionResult(
                userMission.getMission().getId(),
                userMission.getMission().getStore().getName(),
                String.valueOf(userMission.getMission().getRewardPoints()),
                userMission.getStatus(),
                null
        );
    }

    public static UserResponseDto.MyProfile toMyProfile(User user) {
        return new UserResponseDto.MyProfile(
                user.getProfileImageUrl(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getPoint()
        );
    }
}
