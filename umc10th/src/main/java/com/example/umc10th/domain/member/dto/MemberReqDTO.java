package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.FoodName;
import com.example.umc10th.domain.member.enums.Sex;
import lombok.Getter;
import java.util.List;

public class MemberReqDTO {

    // 회원가입 inner에는 DTO를 안붙이는게 관례긴한데...
    @Getter
    public static class SignUpDTO {
        String name;
        Sex sex;
        String birth;
        String address;
        List<FoodName> favoriteFoodTypes;
        List<Long> agreeTermIds;
        String email;
        String password;
    }

    // 마이페이지 - 추가
    public record GetMyPage(
            Long id
    ) {}
}