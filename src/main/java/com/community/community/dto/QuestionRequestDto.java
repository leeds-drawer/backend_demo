// QuestionRequestDto.java
package com.community.community.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class QuestionRequestDto {
    private int    problemNo;     // 백준 번호
    private String title;
    private String contentHtml;
}
