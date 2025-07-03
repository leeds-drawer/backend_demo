// RecommendResponseDto.java
package com.community.community.dto;

import com.community.community.domain.ParentType;
import com.community.community.domain.RecommendType;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RecommendResponseDto {
    private Long          id;
    private String        user;       // 추천자
    private ParentType    parentType;
    private Long          parentId;
    private RecommendType type;
    private String        createdAt;
}
