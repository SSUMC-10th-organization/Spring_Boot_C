package com.example.umc10th.domain.store.service;

import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Override
    public Page<Mission> getStoreMissions(Long storeId, Integer page) {
        // 1. 가게(Store)가 존재하는지 먼저 확인
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 가게가 존재하지 않습니다."));

        // 2. Spring Data JPA의 페이지 번호는 0부터 시작하므로 사용자가 보낸 page 번호에서 -1
        // 한 페이지당 10개씩 가져오도록 설정
        return missionRepository.findAllByStore(store, PageRequest.of(page - 1, 10));
    }
}
