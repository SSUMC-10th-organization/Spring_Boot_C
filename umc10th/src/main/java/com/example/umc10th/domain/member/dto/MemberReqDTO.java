package com.example.umc10th.domain.member.dto;

import lombok.Getter;
import java.util.List;

public class MemberReqDTO {

    // 회원가입 inner에는 DTO를 안붙이는게 관례긴한데...
    @Getter
    public static class SignUpDTO {
        String name;
        String sex;
        String birth;
        String address;
        List<String> favoriteFoodTypes;
        List<Long> agreeTermIds;
    }

    // 마이페이지 - 추가
    public record GetMyPage(
            Long id
    ) {}
}