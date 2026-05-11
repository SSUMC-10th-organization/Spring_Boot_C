package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
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

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    // 홈화면 미션 목록 조회
    public MissionResDTO.Pagination<MissionResDTO.MissionDTO> getMissions(
            Long locationId, Integer pageNumber, Integer pageSize, String sort){

        Sort sortInfo;
        if (sort != null) {
            sortInfo = Sort.by(sort);
        } else {
            sortInfo = Sort.by(Sort.Order.desc("id"));
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);
        Page<Mission> missions = missionRepository.findMissionsByLocationId(locationId, pageRequest);
        return MissionConverter.toPagination(missions.map(MissionConverter::toMissionDTO));
    }

    // 내 미션 목록 조회 (진행중/완료)
    public MissionResDTO.Pagination<MissionResDTO.MissionDTO> getMyMissions(
            Long memberId, String status, Integer pageNumber, Integer pageSize, String sort) {

        Sort sortInfo;
        if (sort != null) {
            sortInfo = Sort.by(sort);
        } else {
            sortInfo = Sort.by(Sort.Order.desc("id"));
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        MissionStatus missionStatus;
        try {
            missionStatus = MissionStatus.valueOf(status);
        } catch (IllegalArgumentException e) {
            throw new MissionException(MissionErrorCode.INVALID_STATUS);
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        Page<MemberMission> memberMissions = memberMissionRepository
                .findByMemberIdAndStatus(memberId, missionStatus, pageRequest);

        return MissionConverter.toPagination(memberMissions.map(MissionConverter::toMyMissionDTO));
    }



}