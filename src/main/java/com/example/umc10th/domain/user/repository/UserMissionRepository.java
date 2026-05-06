package com.example.umc10th.domain.user.repository;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.user.entity.UserMission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    Slice<UserMission> findAllByUser_IdAndStatus(Long userId, MissionStatus status, Pageable pageable);

    Slice<UserMission> findAllByUser_Id(Long userId, Pageable pageable);

    Optional<UserMission> findByUser_IdAndMission_Id(Long userId, Long missionId);
}
