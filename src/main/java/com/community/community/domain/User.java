package com.community.community.domain;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * 사용자 엔티티: 회원가입·로그인 기능에 사용됨
 */
@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK 자동 생성

    @Column(unique = true, nullable = false)
    private String username; // 로그인 아이디

    @Column(nullable = false)
    private String password; // 암호화된 비밀번호

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // 생성 시각
}
