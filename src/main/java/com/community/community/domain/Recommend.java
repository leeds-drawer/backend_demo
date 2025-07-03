package com.community.community.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
@Table(uniqueConstraints =
       @UniqueConstraint(columnNames = {"user_id","parentType","parentId"}))
public class Recommend {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private ParentType parentType;

    @Column(nullable = false)
    private Long parentId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 8)
    private RecommendType type;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
