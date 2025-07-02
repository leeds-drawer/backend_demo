// PostResponseDto.java
package com.community.community.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private String author;
    private String title;
    private String content;
    private String createdAt;
}
