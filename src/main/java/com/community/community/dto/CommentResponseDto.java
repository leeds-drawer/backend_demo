// CommentResponseDto.java
package com.community.community.dto;

import com.community.community.domain.ParentType;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CommentResponseDto {
    private Long       id;
    private String     author;
    private ParentType parentType;
    private Long       parentId;
    private String     content;
    private String     createdAt;
}
