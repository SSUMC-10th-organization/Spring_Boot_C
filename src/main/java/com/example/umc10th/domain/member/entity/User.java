package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "users") // ✅ USER는 예약어이므로 테이블명을 "users"로 지정합니다.
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'NONE'")
    private Gender gender;

    private LocalDate birthDate;

    @Column(length = 255)
    private String address;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer currentPoint;
}
