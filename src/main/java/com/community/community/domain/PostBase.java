// ────────────────── domain/PostBase.java ──────────────────
package com.community.community.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/** SolutionPost · InterviewPost 의 공통 컬럼 */
@Getter @Setter
@MappedSuperclass
public abstract class PostBase extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User author;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private PostCategory category;
}