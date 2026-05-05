package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.enums.TermName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "term")
public class Term {

    @Id
    @Column(name="term_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false)
    @Enumerated(EnumType.STRING)
    private TermName name;
}
