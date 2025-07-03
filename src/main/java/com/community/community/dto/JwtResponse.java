// JwtResponse.java
package com.community.community.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class JwtResponse {
    private String token;
    private String username;
}
