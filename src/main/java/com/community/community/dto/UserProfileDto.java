// UserProfileDto.java
package com.community.community.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserProfileDto {
    private String username;
    private int    xp;
    private int    level;
}
