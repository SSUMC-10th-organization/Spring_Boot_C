package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.Term;
import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import com.example.umc10th.domain.member.entity.mapping.MemberTerm;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.*;
import com.example.umc10th.global.entity.AuthMember;
import com.example.umc10th.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final TermRepository termRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final MemberTermRepository memberTermRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil; // 이 줄 추가

    @Transactional
    // 컨트롤러에서 넘어온 request 객체에서 시작
    public MemberResDTO.SignUpDTO signUp(MemberReqDTO.SignUpDTO request) {
        // 1. Member 저장
        // requset를 넘기면 컨버터안에서 request.getXXX() 이런거로 필요한 값 꺼내
        // Member.builder()로 새 객체를 만든다.
        Member member = MemberConverter.toMember(request, passwordEncoder);
        Member savedMember = memberRepository.save(member);

        // 2. MemberFood 저장
        request.getFavoriteFoodTypes().forEach(foodName -> {
            Food food = foodRepository.findByName(foodName)
                    .orElseThrow(() -> new RuntimeException("음식 없음: " + foodName));
            MemberFood memberFood = MemberConverter.toMemberFood(savedMember, food);
            memberFoodRepository.save(memberFood);
        });

        // 3. MemberTerm 저장
        request.getAgreeTermIds().forEach(termId -> {
            Term term = termRepository.findById(termId)
                    .orElseThrow(() -> new RuntimeException("약관 없음: " + termId));
            MemberTerm memberTerm = MemberConverter.toMemberTerm(savedMember, term);
            memberTermRepository.save(memberTerm);
        });

        return MemberConverter.toSignUpDTO(savedMember);
    }

    public MemberResDTO.Login login(MemberReqDTO.Login request) {
        // 1. 이메일로 회원 조회
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 2. 비밀번호 검증
        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.PASSWORD_NOT_MATCH);
        }

        // 3. JWT 토큰 생성
        String accessToken = jwtUtil.createAccessToken(new AuthMember(member));

        // 4. 응답 DTO로 반환
        return MemberResDTO.Login.builder()
                .accessToken(accessToken)
                .build();
    }
}