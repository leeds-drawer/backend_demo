package com.community.community.domain;

import jakarta.persistence.*;
import lombok.*;

import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity                 // ← 추상 클래스가 아니라 일반 엔티티
@Getter @Setter @NoArgsConstructor @Builder @AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Post extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User author;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private PostCategory category;          // CODING_TEST / TECH_INTERVIEW

    /* Coding-Test 전용(면접 후기는 null) */
    @ManyToOne(fetch = FetchType.LAZY)
    private Problem problem;

    /* 공통 필드 */
    @Column(nullable = false, length = 200)
    private String title;

    @Lob               // 해설 코드
    private String code;

    @Lob               // 해설·후기 본문
    private String explanation;
}
