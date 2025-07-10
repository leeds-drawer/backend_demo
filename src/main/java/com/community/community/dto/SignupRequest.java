// SignupRequest.java
package com.community.community.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SignupRequest {
    private String username;
    private String email;
    private String password;
    private String verificationCode;
}
