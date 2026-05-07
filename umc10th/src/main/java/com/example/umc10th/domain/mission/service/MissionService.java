package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    private final MemberMissionRepository memberMissionRepository;

    // 미션 목록 조회
    public MissionResDTO.MissionListDTO getMissions(Long memberId, MissionStatus status) {

        Page<MemberMission> page = memberMissionRepository.findByMemberIdAndStatus(
                memberId, status, PageRequest.of(0, 10)
        );

        return MissionConverter.toMissionListDTO(page);
    }

    // 미션 상태 업데이트 (진행중 → 진행완료)
    @Transactional
    public MissionResDTO.UpdateMissionStatusDTO updateMissionStatus(Long memberMissionId, MissionStatus status) {

        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new RuntimeException("미션을 찾을 수 없습니다."));

        MemberMission updated = MemberMission.builder()
                .id(memberMission.getId())
                .member(memberMission.getMember())
                .mission(memberMission.getMission())
                .isCompleted(status)
                .build();

        memberMissionRepository.save(updated);

        return MissionResDTO.UpdateMissionStatusDTO.builder()
                .memberMissionId(updated.getId())
                .status(updated.getIsCompleted())
                .build();
    }

    // 홈화면 - 도전 가능한 미션 목록
    public MissionResDTO.HomeMissionListDTO getHomeMissions(Long locationId, int page) {

        Page<Mission> missionPage = missionRepository.findMissionsByLocationId(
                locationId, PageRequest.of(page, 10)
        );

        List<MissionResDTO.HomeMissionItemDTO> missions = missionPage.getContent().stream()
                .map(m -> MissionResDTO.HomeMissionItemDTO.builder()
                        .missionId(m.getId())
                        .storeName(m.getStore().getName())
                        .point(m.getPoint())
                        .condition(m.getCondition())
                        .dDay((int) ChronoUnit.DAYS.between(LocalDate.now(), m.getDeadline()))
                        .build()
                ).toList();

        return MissionResDTO.HomeMissionListDTO.builder()
                .missions(missions)
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }
}