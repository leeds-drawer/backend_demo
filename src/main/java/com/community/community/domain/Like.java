// ────────────────── domain/Like.java ──────────────────
package com.community.community.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor
public class Like {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) private Post post;
    @ManyToOne(optional = false) private User user;

    public Like(Post post, User user) {     // ← ★추가
        this.post = post; this.user = user;
    }
}