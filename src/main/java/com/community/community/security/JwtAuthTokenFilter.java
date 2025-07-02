package com.community.community.security;

import com.community.community.domain.User;
import com.community.community.repository.UserRepository;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

/**
 * 모든 요청마다 실행되어:
 *  1) Authorization 헤더에서 Bearer 토큰 꺼내고
 *  2) JwtUtils로 토큰 검증
 *  3) 토큰이 유효하면 SecurityContext에 Authentication 세팅
 */
@Component
public class JwtAuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String header = request.getHeader("Authorization");
            if (header != null && header.startsWith("Bearer ")) {
                String token = header.substring(7);
                // 1) 토큰 유효성 검사
                if (jwtUtils.validateJwtToken(token)) {
                    // 2) 토큰에서 username 꺼내기
                    String username = jwtUtils.getUserNameFromJwtToken(token);

                    // 3) DB에서 사용자 정보 조회
                    Optional<User> userOpt = userRepo.findByUsername(username);
                    if (userOpt.isPresent()) {
                        User user = userOpt.get();

                        // 4) 인증 객체 생성 (여기선 권한 없이 빈 리스트)
                        UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                user,       // principal
                                null,       // credentials
                                Collections.emptyList() // authorities
                            );
                        auth.setDetails(new WebAuthenticationDetailsSource()
                                            .buildDetails(request));

                        // 5) SecurityContext에 등록
                        SecurityContextHolder.getContext()
                                             .setAuthentication(auth);
                    }
                }
            }
        } catch (JwtException ex) {
            // 토큰 파싱/검증 실패 시 무시하고 다음 필터로 (JwtAuthEntryPoint가 처리)
        }

        // 다음 필터 체인으로
        filterChain.doFilter(request, response);
    }
}
