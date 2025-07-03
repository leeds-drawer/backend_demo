// ────────────────── domain/Level.java ──────────────────
package com.community.community.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor
public class Level {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false) @JoinColumn(name = "user_id")
    private User user;

    private long xp;                    // 누적 경험치

    public Level(User user) {           // ← ★추가
        this.user = user;
    }

    public int getLevel() { return (int)(xp / 10); }

    public void gain(long amount) { this.xp += amount; }
}