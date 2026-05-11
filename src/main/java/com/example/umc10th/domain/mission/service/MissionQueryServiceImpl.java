package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.entity.User;
import com.example.umc10th.domain.member.repository.UserRepository;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final UserRepository userRepository;
    private final UserMissionRepository userMissionRepository;

    // 원래 있던 코드 (그대로 유지)
    @Override
    public Page<UserMission> getMyMissions(Long userId, MissionStatus status, Integer page) {
        // 1. 유저 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        // 2. 유저와 상태를 기준으로 10개씩 페이징하여 조회
        return userMissionRepository.findAllByUserAndStatus(user, status, PageRequest.of(page - 1, 10));
    }

    // 7주차 [미션 1번]을 위해 추가된 코드 (오프셋 페이징, size 파라미터 적용)
    @Override
    public Page<UserMission> getInprogressMissions(Long userId, Integer page, Integer size) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        PageRequest pageRequest = PageRequest.of(page - 1, size); // JPA Page는 0부터 시작

        return userMissionRepository.findAllByUserAndStatus(user, MissionStatus.IN_PROGRESS, pageRequest);
    }
}
