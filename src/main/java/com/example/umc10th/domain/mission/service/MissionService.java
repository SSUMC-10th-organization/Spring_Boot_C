package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    // 홈화면 미션 목록 조회
    public Page<MissionResDTO.MissionDTO> getMissions(Long locationId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Mission> missions = missionRepository.findMissionsByLocationId(locationId, pageRequest);
        return missions.map(MissionConverter::toMissionDTO);
    }

    // 내 미션 목록 조회 (진행중/완료)
    public Page<MissionResDTO.MissionDTO> getMyMissions(Long memberId, String status, int page, int size) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Boolean isComplete = status.equals("COMPLETE");
        PageRequest pageRequest = PageRequest.of(page, size);

        Page<MemberMission> memberMissions = memberMissionRepository
                .findByMemberIdAndIsComplete(memberId, isComplete, pageRequest);

        return memberMissions.map(MissionConverter::toMyMissionDTO);
    }

}