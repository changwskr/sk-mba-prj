package com.sk.mba.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                  HttpServletResponse response, 
                                  FilterChain filterChain) throws ServletException, IOException {
        
        System.out.println("=== JwtAuthenticationFilter.doFilterInternal() 호출됨 ===");
        System.out.println("요청 URL: " + request.getRequestURI());
        
        try {
            // JWT 토큰 추출
            String token = resolveToken(request);
            
            if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
                System.out.println("유효한 JWT 토큰 발견");
                
                // 토큰에서 사용자명 추출
                String username = jwtTokenProvider.getUserName(token);
                
                if (StringUtils.hasText(username)) {
                    System.out.println("인증 처리 중인 사용자: " + username);
                    
                    // UserDetails 생성 (간단한 구현)
                    UserDetails userDetails = User.builder()
                            .username(username)
                            .password("") // JWT 인증에서는 비밀번호가 필요 없음
                            .authorities(Collections.singletonList(new SimpleGrantedAuthority("USER")))
                            .build();
                    
                    // 인증 객체 생성
                    UsernamePasswordAuthenticationToken authentication = 
                            new UsernamePasswordAuthenticationToken(
                                    userDetails, 
                                    null, 
                                    userDetails.getAuthorities());
                    
                    // SecurityContext에 인증 정보 설정
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println("인증 정보가 SecurityContext에 설정됨");
                }
            } else {
                System.out.println("JWT 토큰이 없거나 유효하지 않음");
            }
        } catch (Exception e) {
            System.out.println("JWT 필터 처리 중 오류: " + e.getMessage());
        }
        
        // 다음 필터로 요청 전달
        filterChain.doFilter(request, response);
    }
    
    /**
     * HTTP 요청에서 JWT 토큰 추출
     * @param request HTTP 요청
     * @return JWT 토큰 (없으면 null)
     */
    private String resolveToken(HttpServletRequest request) {
        System.out.println("=== JwtAuthenticationFilter.resolveToken() 호출됨 ===");
        
        String bearerToken = request.getHeader("Authorization");
        System.out.println("Authorization 헤더: " + bearerToken);
        
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7);
            System.out.println("추출된 토큰: " + token.substring(0, Math.min(token.length(), 20)) + "...");
            return token;
        }
        
        // 쿠키에서도 토큰 확인 (웹 애플리케이션용)
        jakarta.servlet.http.Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (jakarta.servlet.http.Cookie cookie : cookies) {
                if ("jwt-token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    System.out.println("쿠키에서 추출된 토큰: " + token.substring(0, Math.min(token.length(), 20)) + "...");
                    return token;
                }
            }
        }
        
        System.out.println("토큰을 찾을 수 없음");
        return null;
    }
} 