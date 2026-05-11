package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 특정 멤버의 미션 상태별 페이징 조회
    // 내가 도전중인 미션들 → MemberMission 테이블 조회
    @Query("SELECT mm FROM MemberMission mm JOIN FETCH mm.mission m JOIN FETCH m.store WHERE mm.member.id = :memberId AND mm.isCompleted = :status")
    Page<MemberMission> findByMemberIdAndStatus(
            @Param("memberId") Long memberId,
            @Param("status") MissionStatus status,
            Pageable pageable
    );

    // 내 진행중인 미션 페이지네이션 조회
    Page<MemberMission> findAllByMember_IdAndIsCompleted(
            Long memberId,
            MissionStatus isCompleted,
            Pageable pageable
    );
}