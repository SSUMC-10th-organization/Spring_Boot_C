package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    @Query("SELECT m FROM Mission m WHERE m.store.location.id = :locationId") //특정 지역에 있는 가게의 미션 가져오기
    Page<Mission> findMissionsByLocationId(
            @Param("locationId") Long locationId,
            Pageable pageable
    );
}
