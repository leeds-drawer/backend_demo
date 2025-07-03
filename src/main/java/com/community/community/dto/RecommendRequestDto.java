// RecommendRequestDto.java
package com.community.community.dto;

import com.community.community.domain.ParentType;
import com.community.community.domain.RecommendType;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RecommendRequestDto {
    private ParentType    parentType;
    private Long          parentId;
    private RecommendType type;       // LIKE / DISLIKE
}
