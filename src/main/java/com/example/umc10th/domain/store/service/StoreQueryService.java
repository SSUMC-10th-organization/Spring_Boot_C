package com.example.umc10th.domain.store.service;

import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

public interface StoreQueryService {
    // 특정 가게의 미션 목록을 페이징해서 가져오는 메서드
    Page<Mission> getStoreMissions(Long storeId, Integer page);
}
