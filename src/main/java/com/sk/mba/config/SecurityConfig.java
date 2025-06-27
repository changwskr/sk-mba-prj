package com.sk.mba.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("SecurityConfig.filterChain 시작");
        System.out.println("=== SecurityConfig.filterChain() 호출됨 ===");
        
        http
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**") // H2 콘솔 CSRF 예외
                .disable()
            )
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // 세션 비활성화 (JWT 사용)
            .authorizeHttpRequests((authz) -> authz
                .requestMatchers("/user/login", "/css/**", "/js/**", "/images/**").permitAll()  // 공개 접근 가능한 경로
                .requestMatchers("/api/auth/**").permitAll()  // Auth API 허용
                .requestMatchers("/h2-console/**").permitAll() // H2 콘솔 허용
                .anyRequest().authenticated()  // 나머지는 인증 필요
            )
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin())) // H2 콘솔 iframe 허용
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);  // JWT 필터 추가
        
        System.out.println("Security 설정 완료");
        System.out.println("SecurityConfig.filterChain 종료");
        return http.build();
    }
} 