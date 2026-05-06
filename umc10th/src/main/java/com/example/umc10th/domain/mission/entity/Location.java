package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.store.entity.Store;
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
@Table(name = "location")
public class Location{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    // 양방향
    // Location(1) : Store(N)
    @OneToMany(mappedBy = "location")
    private List<Store> storeList = new ArrayList<>();
}
