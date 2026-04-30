package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;

public class MemberService {

    private final MemberRespository memberRepository;

    public MemberResDTO.GetInfo getInfo(MemberReqDTO.GetInfo dto){
        //DTO에서 유저 ID 추출
        Long memberId=dto.id();
        //Db에서 해당 유저 ID로 데이터조회
        Member member=memberRepository.findById(memberId)
                .orElseThrow(()->new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        //컨버터를 이용해서 응답 DTO 생성 & return
        return MemberConverter.toGetInfo(member);
    }
}
