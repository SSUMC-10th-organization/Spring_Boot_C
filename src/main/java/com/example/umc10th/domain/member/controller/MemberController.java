package com.example.umc10th.domain.member.controller;


import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    // 마이페이지 조회
    @GetMapping("/me")
    public ApiResponse<MemberResDTO.MyPageDTO> getMyPage(
            @AuthenticationPrincipal AuthMember authMember
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.GET_MY_PAGE_SUCCESS,
                memberService.getMyPage(authMember));
    }
}