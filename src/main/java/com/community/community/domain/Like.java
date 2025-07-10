// ────────────────── domain/Like.java ──────────────────
package com.community.community.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) private Post post;
    @ManyToOne(optional = false) private User user;

    public Like(Post post, User user) {
        this.post = post; this.user = user;
    }
}