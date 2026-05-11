package com.example.umc10th.domain.member.entity.mapping;

import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member_favorite_food")
public class    MemberFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ManyToOne: 우리가 써주는 얘(member, food()가 entity가 1인 입장이다.
    // ManyToOne은 기본값이 즉시 로딩이라, 쿼리가 한번에 여러개 나갈 수 있음. 성능 안좋
    // 현재 Entity인 MemberFood가 항상 N(다) 인 입장

    // member: MemberFood = 1:N
    // 단뱡향관계 : MemberFood → Member
    // member가 1임
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member; // 이게 있어야 mapped by를 쓸수있다.

    // food: MemberFood = 1:N
    // 단방향관계: MemberFood → Food
    // food가 1임
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="food_id")
    private Food food;
}
