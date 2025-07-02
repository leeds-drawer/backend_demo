// CommentResponseDto.java
package com.community.community.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String author;
    private String content;
    private String createdAt;
}
