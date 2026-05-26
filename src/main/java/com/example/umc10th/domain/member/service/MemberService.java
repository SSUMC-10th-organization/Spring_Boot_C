package com.example.umc10th.domain.member.service;


import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.global.security.entity.AuthMember;
import com.example.umc10th.global.security.service.CustomUserDetailsService;
import com.example.umc10th.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;


    // 마이페이지 조회
    public MemberResDTO.MyPageDTO getMyPage(AuthMember authMember) {
        Member member = memberRepository.findById(authMember.getMember().getId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toMyPageDTO(member);
    }

    @Transactional
    public MemberResDTO.SignUpDTO signUp(MemberReqDTO.SignUpDTO request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        Member member = MemberConverter.toMember(request, encodedPassword);
        return MemberConverter.toSignUpDTO(memberRepository.save(member));
    }

    @Transactional
    public MemberResDTO.LoginDTO login(MemberReqDTO.LoginDTO request) {
        // 이메일로 사용자 조회
        AuthMember authMember = (AuthMember) customUserDetailsService
                .loadUserByUsername(request.getEmail());

        // 비밀번호 검증
        if (!passwordEncoder.matches(request.getPassword(), authMember.getPassword())) {
            throw new MemberException(MemberErrorCode.INVALID_PASSWORD);
        }

        // JWT 토큰 발급
        String accessToken = jwtUtil.createAccessToken(authMember);

        return MemberResDTO.LoginDTO.builder()
                .accessToken(accessToken)
                .build();
    }

}