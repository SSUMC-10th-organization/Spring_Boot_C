package com.example.umc10th.domain.member.dto;

import lombok.Getter;
import java.util.List;

public class MemberReqDTO {

    @Getter
    public static class SignUpDTO {
        String name;
        String sex;
        String birth;
        String address;
        List<String> favoriteFoodTypes;
        List<Long> agreeTermIds;
    }
}