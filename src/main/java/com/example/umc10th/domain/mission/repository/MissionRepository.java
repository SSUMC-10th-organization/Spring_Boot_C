package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    // 가게(Store) 객체를 기준으로 페이징된 미션 목록을 가져옵니다.
    Page<Mission> findAllByStore(Store store, Pageable pageable);
}
