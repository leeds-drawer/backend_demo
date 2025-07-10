// ────────────────── domain/InterviewPost.java ──────────────────
package com.community.community.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import com.community.community.converter.TagListConverter;

@Entity @Getter @Setter @NoArgsConstructor
public class InterviewPost extends Post {

    @Lob private String content;   // 면접후기 본문

    @Convert(converter = TagListConverter.class)
    private List<String> tags = new ArrayList<>();
}