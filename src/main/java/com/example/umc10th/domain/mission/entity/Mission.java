package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.region.entity.Region;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer rewardPoint; // 보상 포인트

    @Column(nullable = false)
    private Integer conditionPrice; // 최소 조건 금액

    @Column(nullable = false, length = 50)
    private String content; // 미션 내용

    // 미션(N) : 가게(1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    // 미션(N) : 지역(1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;
}
