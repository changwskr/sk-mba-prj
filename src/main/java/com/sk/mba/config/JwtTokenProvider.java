package com.sk.mba.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {
    
    @Value("${jwt.secret:defaultSecretKey123456789012345678901234567890}")
    private String jwtSecret;
    
    @Value("${jwt.expiration:86400000}") // 24시간 (밀리초)
    private long jwtExpiration;
    
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }
    
    /**
     * JWT 토큰 생성
     * @param username 사용자명
     * @return 생성된 JWT 토큰
     */
    public String createToken(String username) {
        System.out.println("=== JwtTokenProvider.createToken() 호출됨 ===");
        System.out.println("토큰 생성 대상 사용자: " + username);
        
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);
        
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
        
        System.out.println("JWT 토큰 생성 완료");
        return token;
    }
    
    /**
     * JWT 토큰에서 사용자명 추출
     * @param token JWT 토큰
     * @return 사용자명
     */
    public String getUserName(String token) {
        System.out.println("=== JwtTokenProvider.getUserName() 호출됨 ===");
        
        if (!StringUtils.hasText(token)) {
            System.out.println("토큰이 비어있음");
            return null;
        }
        
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            
            String username = claims.getSubject();
            System.out.println("토큰에서 추출된 사용자명: " + username);
            return username;
        } catch (Exception e) {
            System.out.println("토큰에서 사용자명 추출 실패: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * JWT 토큰 유효성 검증
     * @param token JWT 토큰
     * @return 유효성 여부
     */
    public boolean validateToken(String token) {
        System.out.println("=== JwtTokenProvider.validateToken() 호출됨 ===");
        
        if (!StringUtils.hasText(token)) {
            System.out.println("토큰이 비어있음");
            return false;
        }
        
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            
            System.out.println("토큰 유효성 검증 성공");
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("토큰 만료: " + e.getMessage());
            return false;
        } catch (UnsupportedJwtException e) {
            System.out.println("지원되지 않는 토큰: " + e.getMessage());
            return false;
        } catch (MalformedJwtException e) {
            System.out.println("잘못된 토큰 형식: " + e.getMessage());
            return false;
        } catch (SecurityException e) {
            System.out.println("토큰 서명 검증 실패: " + e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println("토큰이 비어있음: " + e.getMessage());
            return false;
        }
    }
} 