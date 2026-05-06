package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import com.example.umc10th.domain.member.entity.mapping.MemberTerm;
import com.example.umc10th.domain.member.enums.Sex;
import com.example.umc10th.domain.member.enums.SocialType;
import com.example.umc10th.global.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @Column(name = "member_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "sex", nullable = false)
    @Enumerated(EnumType.STRING) // enum이면 @Enumerated 추가
    private Sex sex;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "point", nullable = true)
    private Integer point;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "phone_number", nullable = true)
    private String phoneNumber;

    @Column(name = "address", nullable = false)
    private String address;

    // 탈퇴여부
    @Column(name = "is_left", nullable = false)
    private Boolean isLeft;

    @Column(name = "profile_img_url", nullable = true)
    private String profileUrl;   // Converter에서 getProfileUrl() 호출하므로 이 이름

    // 소셜 로그인 고려해서 컬럼추가(erd엔 반영안되있음)
    @Column(name="social_uid", nullable = false)
    private String socialUid;

    // 어떤 SNS 로그인인지
    @Column(name="social_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    // 양방향 매핑
    // onetomany: 1:N관계에서
    // 멤버가 1, memberfood, memberterm이 N이다.
    @OneToMany(mappedBy = "member")
    private List<MemberFood> memberFoodList = new ArrayList<>();

    @OneToMany(mappedBy = "member")  // MemberTerm 안의 member 필드
    private List<MemberTerm> memberTermList = new ArrayList<>();

}
