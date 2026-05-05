package com.example.umc10th.domain.user.converter;

import com.example.umc10th.domain.user.dto.UserRequestDto;
import com.example.umc10th.domain.user.dto.UserResponseDto;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.entity.UserMission;

public class UserConverter {

    public static User toUser(UserRequestDto.SignUp request) {
        return User.builder()
                .name(request.name())
                .gender(request.gender())
                .birthday(request.birthday())
                .address(request.address())
                .socialType(request.socialType())
                .point(0)
                .build();
    }

    public static UserResponseDto.SignUpResult toSignUpResult(User user) {
        return new UserResponseDto.SignUpResult(
                user.getId(),
                user.getName(),
                user.getCreatedAt()
        );
    }

    public static UserResponseDto.CompleteMissionResult toCompleteMissionResult(UserMission userMission) {
        return new UserResponseDto.CompleteMissionResult(
                userMission.getId(),
                userMission.getStatus()
        );
    }
}
