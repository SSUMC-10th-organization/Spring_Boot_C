package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class UserRequestDTO {

    @Getter
    @NoArgsConstructor
    public static class SignupDTO {
        private String email;
        private String password;
        private String name;
        private Gender gender;
        private LocalDate birthDate;
        private String address;
    }

    @Getter
    @NoArgsConstructor
    public static class LoginDTO {
        private String email;
        private String password;
    }
}
