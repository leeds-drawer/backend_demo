// InterviewPostRequestDto.java
package com.community.community.dto;

import java.util.List;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class InterviewPostRequestDto {
    private String title;
    private String contentHtml;       // 후기 본문
    private List<String> tags;        // 사용자가 자유 입력
}
