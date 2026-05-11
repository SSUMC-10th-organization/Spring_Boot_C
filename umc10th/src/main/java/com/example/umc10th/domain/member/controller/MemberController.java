package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name = "Member", description = "회원 관련 API")
public class MemberController {

    private final MemberService memberService;

    // 마이페이지 - 회원 정보 조회
    @Operation(summary = "마이페이지 정보 조회") // SwaggerUI에 /me 옆에 마이페이지 정보조회라고 뜸(프론트를 위해)
    @GetMapping("/me")
    public ApiResponse<MemberResDTO.MyPageInfo> getMyPage(
            @RequestParam Long memberId
    ) {
        // 리턴에서 서비스 호출 + 응답을 포장
        return ApiResponse.onSuccess(
                MemberSuccessCode.MEMBER_INFO_OK,
                memberService.getMyPage(memberId)
        );
    }

    // 마이페이지 - 작성한 리뷰 페이징 조회
    @Operation(summary = "내가 작성한 리뷰 페이징 조회")
    @GetMapping("/{memberId}/reviews")
    public ApiResponse<MemberResDTO.MyPageReviewList> getMyReviews(
            @PathVariable Long memberId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(
                MemberSuccessCode.MEMBER_REVIEW_OK,
                memberService.getMyReviews(memberId, page, size)
        );
    }
}