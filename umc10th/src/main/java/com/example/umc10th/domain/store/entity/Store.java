package com.example.umc10th.domain.store.entity;

import com.example.umc10th.domain.mission.entity.Location;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.global.entity.BaseEntity;
import lombok.*;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    // 사장님 구분 번호
    @Column(name = "manager_number", nullable = false)
    private String managerNumber;

    @Column(name = "address", nullable = false)
    private String address;

    // 평점(가게마다)
    @Column(name = "star", nullable = true)
    private Double star;

    // Location(1) : Store(N)
    // Store가 N이니까 @ManyToOne
    // 가게보고 어떤 지역에 있는지 조회할 수 있으니까 양방향
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    // 양방향
    // Store(1) : Mission(N)
    @OneToMany(mappedBy = "store")
    private List<Mission> missionList = new ArrayList<>();

    // 양방향
    // Store(1) : Review(N)
    @OneToMany(mappedBy = "store")
    private List<Review> reviewList = new ArrayList<>();

}
