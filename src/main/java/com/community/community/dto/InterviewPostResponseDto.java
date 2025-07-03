// InterviewPostResponseDto.java
package com.community.community.dto;

import java.util.List;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class InterviewPostResponseDto {
    private Long   id;
    private String author;
    private String title;
    private String contentHtml;
    private List<String> tags;

    private int likeCnt;
    private int dislikeCnt;
    private int cmntCnt;
    private String createdAt;
}
