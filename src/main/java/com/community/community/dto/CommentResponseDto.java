package com.community.community.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor          // ★ 추가 – 4개 파라미터 생성자 자동 생성
@Builder                     // (선택) 빌더로도 만들고 싶으면 유지
public class CommentResponseDto {

    private Long   id;
    private String username;   // ← author → username 으로 통일
    private String content;
    private String createdAt;
}
