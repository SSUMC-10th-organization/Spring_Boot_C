package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.member.entity.User;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    // 유저(User)와 미션 상태(Status)를 기준으로 페이징된 내 미션 목록을 가져옵니다.
    Page<UserMission> findAllByUserAndStatus(User user, MissionStatus status, Pageable pageable);
}
