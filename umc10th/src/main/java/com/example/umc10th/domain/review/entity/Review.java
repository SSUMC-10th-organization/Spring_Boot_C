package com.example.umc10th.domain.review.entity;

import jakarta.persistence.*;

public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 리뷰-리뷰답글관계 1:1
    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="reply_id")
    private Reply reply;
}
