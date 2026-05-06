package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mission")
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 미션 기한(erd에 추가하기)
    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    // 미션 조건
    @Column(name = "condition", nullable = false, columnDefinition = "TEXT")
    private String condition;

    // 성공 포인트
    @Column(name = "point", nullable = false)
    private Integer point;

    // 가게(1) : 미션(N)
    // Mission 입장에서 N이니까 @ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    // 양방향
    // Mission(1) : MemberMission(N)
    @OneToMany(mappedBy = "mission")
    private List<MemberMission> memberMissionList = new ArrayList<>();
}
