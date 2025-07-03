// ────────────────── domain/PostTag.java ──────────────────
package com.community.community.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor
public class PostTag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) private Post post;
    @ManyToOne(optional = false) private Tag  tag;

    public PostTag(Post post, Tag tag) {    // ← ★추가
        this.post = post; this.tag = tag;
    }
}