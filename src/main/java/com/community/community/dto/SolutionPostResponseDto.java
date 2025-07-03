// SolutionPostResponseDto.java
package com.community.community.dto;

import java.util.List;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SolutionPostResponseDto {
    private Long   id;
    private String author;            // 작성자 닉네임
    private int    problemNo;
    private String title;
    private String contentHtml;
    private String code;
    private String imageUrl;
    private List<String> tags;

    private int likeCnt;
    private int dislikeCnt;
    private int cmntCnt;
    private String createdAt;         // ISO-8601 문자열
}
