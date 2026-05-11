package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;

public interface MissionQueryService {
    Page<UserMission> getMyMissions(Long userId, MissionStatus status, Integer page);

    // 7주차 미션 1번을 위해 추가
    Page<UserMission> getInprogressMissions(Long userId, Integer page, Integer size);
}
