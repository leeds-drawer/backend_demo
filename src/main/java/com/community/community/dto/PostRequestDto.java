// PostRequestDto.java  (기존 파일 교체)
package com.community.community.dto;

import com.community.community.domain.PostCategory;
import lombok.*;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PostRequestDto {
    private PostCategory category;   // CODING_TEST | TECH_INTERVIEW
    private Long   problemId;        // CodingTest 전용 (nullable)
    private String title;
    private String code;             // 소스코드
    private String explanation;      // 해설‧후기 본문
    private List<String> tags;       // 해시태그 문자열 모음
}