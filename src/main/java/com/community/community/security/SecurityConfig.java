// src/main/java/com/community/community/security/SecurityConfig.java
package com.community.community.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthEntryPoint unauthorizedHandler;
    private final JwtAuthTokenFilter jwtAuthTokenFilter;

    public SecurityConfig(JwtAuthEntryPoint unauthorizedHandler,
                          JwtAuthTokenFilter jwtAuthTokenFilter) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.jwtAuthTokenFilter = jwtAuthTokenFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
          // REST API이므로 CSRF 끄기
          .csrf(csrf -> csrf.disable())

          // HTTP Basic Auth, Form Login 둘 다 끄기
          .httpBasic(basic -> basic.disable())
          .formLogin(login -> login.disable())

          // 인증/인가 예외 처리
          .exceptionHandling(ex -> ex
              .authenticationEntryPoint(unauthorizedHandler)
          )

          // 세션 사용 안 함
          .sessionManagement(sm -> sm
              .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          )

          // 경로별 접근 제어
          .authorizeHttpRequests(auth -> auth
              .requestMatchers("/auth/**", "/h2-console/**").permitAll()
              .anyRequest().authenticated()
          )

          // JWT 필터 등록
          .addFilterBefore(jwtAuthTokenFilter,
                           UsernamePasswordAuthenticationFilter.class)

          // H2 콘솔 프레임 옵션 해제
          .headers(headers -> headers.frameOptions(frame -> frame.disable()))
          ;

        return http.build();
    }
}
