package com.example.umc10th.domain.mission.entity.mapping;

import com.example.umc10th.domain.member.entity.User;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

// 상태 Enum이 있다면 임포트 필요 (예: import com.example.umc10th.domain.mission.enums.MissionStatus;)

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'IN_PROGRESS'")
    private MissionStatus status; // 상태

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;
}
