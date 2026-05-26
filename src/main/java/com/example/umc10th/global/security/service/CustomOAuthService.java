package com.example.umc10th.global.security.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.SocialType;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.global.security.dto.KakaoDTO;
import com.example.umc10th.global.security.dto.OAuthDTO;
import com.example.umc10th.global.security.entity.OAuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuthService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 카카오 서버에서 사용자 정보 받아오기
        OAuth2User oAuthMember = super.loadUser(userRequest);

        // 카카오 계정 정보 추출
        Map<String, Object> attributes = oAuthMember.getAttribute("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) attributes.get("profile");

        // 카카오 고유 ID
        String socialUid = String.valueOf((Long) oAuthMember.getAttribute("id"));
        // 이메일
        String email = attributes.get("email").toString();
        // 닉네임
        String name = profile.get("nickname").toString();

        // KakaoDTO로 변환
        OAuthDTO dto = new KakaoDTO(socialUid, email, name);

        // DB 저장 or 조회
        Member member = memberRepository.findBySocialTypeAndSocialUid(SocialType.KAKAO, socialUid)
                .orElseGet(() -> {
                    Member newMember = MemberConverter.toOAuthMember(dto);
                    return memberRepository.save(newMember);
                });

        return new OAuthMember(member, oAuthMember.getAttributes());
    }
}