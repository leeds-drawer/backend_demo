// ────────────────── domain/XpLog.java ──────────────────
package com.community.community.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor
public class XpLog extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) private User  user;
    private long delta;          // +경험치
    private String reason;       // 예) "좋아요 10개 달성"
}