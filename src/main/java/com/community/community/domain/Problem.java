// ────────────────── domain/Problem.java ──────────────────
package com.community.community.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor
public class Problem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 백준 문제 번호 (Unique) */
    @Column(unique = true, nullable = false)
    private Long number;

    /** 난이도, 제목 등 메타데이터를 가져다 쓸 수도 있음 */
}