package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.UserRequestDTO;
import com.example.umc10th.domain.member.dto.UserResponseDTO;
import com.example.umc10th.domain.member.entity.User;
import com.example.umc10th.domain.member.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public class UserConverter {

    // 기획서 화면 2번(기본정보) + 미션(이메일, 비밀번호) 반영
    public static User toUser(UserRequestDTO.SignupDTO request, String encodedPassword) {
        // 성별 처리
        Gender gender = Gender.NONE;
        if (request.getGender() != null) {
            switch (request.getGender().toUpperCase()) {
                case "MALE": gender = Gender.MALE; break;
                case "FEMALE": gender = Gender.FEMALE; break;
            }
        }

        // 생년월일 처리 (클라이언트가 "2000-01-01" 형태로 보낸다고 가정)
        LocalDate birthDate = null;
        if (request.getBirthDate() != null && !request.getBirthDate().isEmpty()) {
            birthDate = LocalDate.parse(request.getBirthDate());
        }

        return User.builder()
                .email(request.getEmail())
                .password(encodedPassword) // 암호화된 비밀번호
                .name(request.getName())
                .gender(gender)
                .birthDate(birthDate)      // 생년월일 추가
                .address(request.getAddress()) // 주소 추가
                .currentPoint(0)
                .build();
    }

    // ---------------- 아래는 기존 코드 ----------------
    public static UserResponseDTO.SignupResultDTO toSignupResultDTO(Long memberId, String email, String name) {
        return UserResponseDTO.SignupResultDTO.builder()
                .memberId(memberId)
                .email(email)
                .name(name)
                .build();
    }
}
