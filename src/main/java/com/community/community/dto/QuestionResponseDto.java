// QuestionResponseDto.java
package com.community.community.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class QuestionResponseDto {
    private Long   id;
    private String author;
    private int    problemNo;
    private String title;
    private String contentHtml;

    private int    cmntCnt;
    private String createdAt;
}
