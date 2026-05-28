package com.example.umc10th.domain.member.dto;

import lombok.*;

public class UserResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignupResultDTO {
        private Long userId;
        private String email;
        private String name;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginResultDTO {
        private String accessToken;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyPageResultDTO {
        private Long userId;
        private String email;
        private String name;
        private String gender;
        private String birthDate;
        private String address;
        private Integer currentPoint;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HomeResultDTO {
        private String title;
        private String message;
    }
}
