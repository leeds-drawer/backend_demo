// SolutionPostRequestDto.java
package com.community.community.dto;

import java.util.List;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SolutionPostRequestDto {
    private int problemNo;            // 백준 문제 번호
    private String title;
    private String contentHtml;       // 해설(HTML)
    private String code;              // 원본 코드
    private String imageUrl;          // 문제 캡처 S3 URL
    private List<String> tags;        // 난도, 알고리즘명 등
}
