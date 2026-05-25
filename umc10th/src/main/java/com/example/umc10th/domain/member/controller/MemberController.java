package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.entity.AuthMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/api/v1/users")
@Tag(name = "Member", description = "회원 관련 API")
public class MemberController {

    private final MemberService memberService;

    // 1. 마이페이지 - 회원 정보 조회
//    @Operation(summary = "마이페이지 정보 조회_1") // SwaggerUI에 /me 옆에 마이페이지 정보조회라고 뜸(프론트를 위해)
//    @GetMapping("/v1/users/me")
//    public ApiResponse<MemberResDTO.MyPageInfo> getMyPage(
//            @RequestParam Long memberId
//    ) {
//        // 리턴에서 서비스 호출 + 응답을 포장
//        return ApiResponse.onSuccess(
//                MemberSuccessCode.MEMBER_INFO_OK,
//                memberService.getMyPage(memberId)
//        );
//    }

    // 2. 마이페이지 - 헤더에담긴 토큰으로 사용자 정보 return하기
    @Operation(summary = "마이페이지 정보 조회_2 헤더에 담긴 토큰 이용") // SwaggerUI에 /me 옆에 마이페이지 정보조회라고 뜸(프론트를 위해)

    @GetMapping("/v2/users/me")
    public ApiResponse<MemberResDTO.MyPageInfo> getMyPage(
            @AuthenticationPrincipal AuthMember member
            ){
        BaseSuccessCode code=MemberSuccessCode.MEMBER_INFO_OK;
        return ApiResponse.onSuccess(code, memberService.getMyPage(member));
    }

    // 마이페이지 - 작성한 리뷰 페이징 조회
    // 1) 오프셋기반
//    @Operation(summary = "내가 작성한 리뷰 페이징 조회")
//    @GetMapping("/{memberId}/reviews")
//    public ApiResponse<MemberResDTO.MyPageReviewList> getMyReviews(
//            @PathVariable Long memberId,
//            @RequestParam(defaultValue = "0") Integer page,
//            @RequestParam(defaultValue = "10") Integer size
//    ) {
//        return ApiResponse.onSuccess(
//                MemberSuccessCode.MEMBER_REVIEW_OK,
//                memberService.getMyReviews(memberId, page, size)
//        );
//    }
    @Operation(summary = "내가 작성한 리뷰 커서 페이징 조회 (id순 / 별점순)")
    @GetMapping("/{memberId}/reviews/cursor")
    public ApiResponse<MemberResDTO.MyPageReviewCursorList> getMyReviewsByCursor(
            @PathVariable Long memberId,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "-1") String cursor,
            @RequestParam(defaultValue = "id") String query
    ) {
        return ApiResponse.onSuccess(
                MemberSuccessCode.MEMBER_REVIEW_OK,
                memberService.getMyReviewsByCursor(memberId, pageSize, cursor, query)
        );
    }
}
