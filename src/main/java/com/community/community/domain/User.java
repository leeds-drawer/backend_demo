package com.community.community.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 30)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    /** 경험치·레벨 **/
    @Column(nullable = false) private int xp = 0;
    @Column(nullable = false) private int level = 0;

    /* ------ 비즈니스 ------ */
    public void gainXp(int delta) {
        this.xp += delta;
        int newLevel = xp / 10;
        if (newLevel != level) this.level = newLevel;
    }
}
