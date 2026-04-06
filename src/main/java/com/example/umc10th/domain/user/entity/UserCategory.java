package com.example.umc10th.domain.user.entity;

import com.example.umc10th.common.entity.BaseEntity;
import com.example.umc10th.domain.category.entity.Category;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_categories")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
