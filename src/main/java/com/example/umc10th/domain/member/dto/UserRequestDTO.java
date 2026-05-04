package com.example.umc10th.domain.member.dto;

import lombok.Getter;

import java.util.List;

public class UserRequestDTO {

    @Getter
    public static class SignupDTO {
        private String email;
        private String password;
        private String name;
        private String birthDate;
        private String gender;
        private String phoneNumber;
        private String address;
        private List<String> preferredFoodCategories;
        private Boolean termsAgreed;
        private Boolean privacyAgreed;
    }
}
