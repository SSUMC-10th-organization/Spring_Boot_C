package com.example.umc10th.domain.user.repository;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.user.entity.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    List<UserMission> findAllByUser_IdAndStatus(Long userId, MissionStatus status);

    Optional<UserMission> findByUser_IdAndMission_Id(Long userId, Long missionId);
}
