package com.example.umc10th.domain.review.entity;

import com.example.umc10th.global.entity.BaseEntity;
import lombok.*;
import jakarta.persistence.*;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review_photo")
public class ReviewPhoto{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "photo_url", nullable = true, columnDefinition = "TEXT")
    private String photoUrl;

    // 1:N을 나타내고 싶은데, 양방향을 걸기싫으면 한쪽에만 ManyToOne
    // Review(1) : ReviewPhoto(N)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;
}