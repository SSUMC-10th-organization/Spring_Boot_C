package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResponseDto;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.user.entity.UserMission;
import com.example.umc10th.domain.user.repository.UserMissionRepository;
import com.example.umc10th.domain.user.repository.UserRepository;
import com.example.umc10th.global.apipayload.code.GeneralErrorCode;
import com.example.umc10th.global.apipayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;

    public MissionResponseDto.MyMissionListResult getMyMissions(Long userId, int page) {
        if (!userRepository.existsById(userId)) {
            throw new GeneralException(GeneralErrorCode.USER_NOT_FOUND);
        }
        PageRequest pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "id"));
        Page<UserMission> result = userMissionRepository.findPageByUser_IdAndStatus(userId, MissionStatus.CHALLENGING, pageable);
        return MissionConverter.toMyMissionListResult(result);
    }
}
