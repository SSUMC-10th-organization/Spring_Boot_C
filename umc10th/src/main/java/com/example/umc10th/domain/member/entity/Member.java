package com.example.umc10th.domain.member.entity;

import lombok.Getter;
import jakarta.persistence.*;

@Getter
@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Enumerated(EnumType.STRING) // enum이면 @Enumerated 추가
    @Column(name = "sex")
    private String sex;

    @Column(name = "birth")
    private java.time.LocalDate birth;

    @Column(name = "point")
    private Integer point;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "is_left")
    private Boolean isLeft;

    @Column(name = "profile_img_url")
    private String profileUrl;   // Converter에서 getProfileUrl() 호출하므로 이 이름
}