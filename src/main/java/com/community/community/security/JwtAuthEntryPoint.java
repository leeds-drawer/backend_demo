package com.community.community.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 인증 실패 또는 토큰 유효하지 않을 때 401 Unauthorized 응답을 처리
 */
@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException, ServletException {

        // 응답 헤더, 상태 코드 설정
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, 
                           "Error: Unauthorized");
    }
}
