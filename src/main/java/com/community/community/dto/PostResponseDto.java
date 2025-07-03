// PostResponseDto.java  (기존 파일 교체)
package com.community.community.dto;

import com.community.community.domain.PostCategory;
import lombok.*;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private String author;
    private PostCategory category;
    private Long   problemId;         // null ⇒ TECH_INTERVIEW
    private String title;
    private String code;
    private String explanation;
    private String createdAt;
    private long   likeCount;
    private List<String> tags;
}