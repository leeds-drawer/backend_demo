// ────────────────── domain/SolutionPost.java ──────────────────
package com.community.community.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter @NoArgsConstructor
@Table(indexes = @Index(columnList = "problemNo"))
public class SolutionPost extends Post {

    /** 백준 문제 번호 */
    @Column(nullable = false)
    private long problemNo;

    @Lob private String code;
    @Lob private String explanation;

    @Convert(converter = TagListConverter.class)
    private List<String> tags = new ArrayList<>();
}
