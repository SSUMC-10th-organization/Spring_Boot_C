package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.common.entity.BaseEntity;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.user.entity.UserMission;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "missions")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(length = 30)
    private String title;

    @Column(length = 100)
    private String description;

    @Column(name = "reward_points")
    private Integer rewardPoints;

    @Column(name = "deadline_day")
    private LocalDateTime deadlineDay;

    @Builder.Default
    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<UserMission> userMissions = new ArrayList<>();
}
