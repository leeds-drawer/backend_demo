// CommentRequestDto.java
package com.community.community.dto;

import com.community.community.domain.ParentType;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CommentRequestDto {
    private ParentType parentType; // SOLUTION / INTERVIEW / QUESTION
    private Long       parentId;   // 게시물 PK
    private String     content;
}
