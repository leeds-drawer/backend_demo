// ────────────────── domain/Question.java ──────────────────
package com.community.community.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor
public class Question extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) private User    author;
    @ManyToOne(optional = false) private Problem problem;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
}