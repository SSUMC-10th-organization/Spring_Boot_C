package com.example.umc10th.domain.review.entity;

import com.example.umc10th.domain.member.entity.User;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer rating; // 별점

    @Column(length = 255)
    private String content; // 리뷰 내용

    @Column(length = 255)
    private String imageUrl; // 사진 URL

    // 리뷰(N) : 유저(1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 리뷰(N) : 가게(1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}
