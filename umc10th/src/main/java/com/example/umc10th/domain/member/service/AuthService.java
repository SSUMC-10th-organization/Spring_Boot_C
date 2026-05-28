package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.Term;
import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import com.example.umc10th.domain.member.entity.mapping.MemberTerm;
import com.example.umc10th.domain.member.repository.*;
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
}