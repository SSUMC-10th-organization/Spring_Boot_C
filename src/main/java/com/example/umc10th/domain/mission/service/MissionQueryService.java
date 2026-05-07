package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;

public interface MissionQueryService {
    // 유저 ID와 상태(진행중/완료)를 받아서 페이징된 내 미션 목록을 반환합니다.
    Page<UserMission> getMyMissions(Long userId, MissionStatus status, Integer page);
}
