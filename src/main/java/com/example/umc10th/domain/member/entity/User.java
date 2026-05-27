package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 로그인에 사용할 이메일 추가 (보통 유니크 제약조건을 걸음)
    @Column(nullable = false, unique = true, length = 50)
    private String email;

    // 암호화된 비밀번호를 저장할 필드 추가
    @Column(nullable = false)
    private String password;

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
