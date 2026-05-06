package com.example.umc10th.domain.review.entity;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.global.entity.BaseEntity;
import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;


@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 리뷰내용
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    // 평점
    @Column(name = "star", nullable = false)
    private BigDecimal star;


    // baseEntity안에 생성일자 있으니까 별도 선언 x

    // Store(1) : Review(N)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    // Member(1) : Review(N)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 리뷰-리뷰답글관계 1:1
    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="reply_id")
    private Reply reply;


}
