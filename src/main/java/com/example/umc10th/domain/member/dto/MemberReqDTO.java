package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import lombok.Getter;
import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {
    //회원가입
    @Getter
    public static class SignUpDTO {
        String id;
        String password;
        String name;
        Gender gender;
        LocalDate birthDate;
        String address;
        String email;
        String phoneNumber;
        List<String> favoriteFood;
    }

    @Getter
    public static class LoginDTO {
        String email;
        String password;
    }
}
