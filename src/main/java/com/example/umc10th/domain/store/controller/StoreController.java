package com.example.umc10th.domain.store.controller;

import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.store.service.StoreQueryService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class StoreController {

    private final StoreQueryService storeQueryService;

    // 특정 가게의 미션 목록 조회 API
    @GetMapping("/{storeId}/missions")
    public ApiResponse<Object> getStoreMissions(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        // 방금 만든 가게 미션 페이징 서비스 호출!
        Page<Mission> missionPage = storeQueryService.getStoreMissions(storeId, page);

        // (추후 과제) 여기서도 Converter를 이용해 Page<Mission>을 응답 DTO로 바꿔야 하지만,
        // 지금은 서비스 연결까지만 확인하기 위해 임시 성공 메시지를 반환합니다.
        return ApiResponse.onSuccess("가게 미션 목록 조회 성공!");
    }
}
