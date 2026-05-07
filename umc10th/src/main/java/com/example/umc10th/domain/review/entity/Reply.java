package com.example.umc10th.domain.review.entity;

import com.example.umc10th.global.entity.BaseEntity;
import lombok.*;
import jakarta.persistence.*;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reply")
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    // Review(1) : Reply(1) - 1:1 관계
    // 답글을 보고 리뷰원문에 접근할 수 있게
    @OneToOne(mappedBy = "reply")
    private Review review;  // Reply → Review 접근 가능

}