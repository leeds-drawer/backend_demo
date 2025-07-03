package com.community.community.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
@Table(indexes = @Index(columnList = "problemNo"))
public class Question extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    @Column(nullable = false)
    private int problemNo;

    @Column(nullable = false)
    private String title;

    @Lob @Column(nullable = false)
    private String contentHtml;

    @Column(nullable = false) private int cmntCnt = 0;
}
