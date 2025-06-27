package com.sk.mba.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sk.mba.user.service.UserService;
import com.sk.mba.user.model.User;
import com.sk.mba.config.JwtTokenProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    /**
     * 로그인 API - JWT 토큰 발급
     * @param loginRequest 로그인 요청 데이터
     * @param response HTTP 응답 객체
     * @return 로그인 결과
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginRequest,
                                                    HttpServletResponse response) {
        System.out.println("=== AuthController.login() 호출됨 ===");
        
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");
        
        System.out.println("로그인 요청 - username: " + username);
        
        Map<String, Object> result = new HashMap<>();
        
        try {
            // UserService를 통한 인증
            User authenticatedUser = userService.authenticate(username, password);
            
            if (authenticatedUser != null) {
                System.out.println("인증 성공: " + authenticatedUser.getUsername());
                
                // JWT 토큰 생성
                String jwtToken = jwtTokenProvider.createToken(authenticatedUser.getUsername());
                System.out.println("JWT 토큰 생성 완료");
                
                // JWT 토큰을 쿠키에 저장
                Cookie jwtCookie = new Cookie("jwt-token", jwtToken);
                jwtCookie.setHttpOnly(true);
                jwtCookie.setSecure(false); // 개발환경에서는 false, 운영환경에서는 true
                jwtCookie.setPath("/");
                jwtCookie.setMaxAge(24 * 60 * 60); // 24시간
                response.addCookie(jwtCookie);
                System.out.println("JWT 토큰이 쿠키에 저장됨");
                
                // 응답 데이터 구성
                result.put("success", true);
                result.put("message", "로그인 성공");
                result.put("token", jwtToken);
                result.put("username", authenticatedUser.getUsername());
                result.put("name", authenticatedUser.getName());
                
                return ResponseEntity.ok()
                        .header("Content-Type", "application/json; charset=UTF-8")
                        .body(result);
            } else {
                System.out.println("인증 실패: 잘못된 사용자 정보");
                
                result.put("success", false);
                result.put("message", "아이디 또는 비밀번호가 올바르지 않습니다.");
                
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .header("Content-Type", "application/json; charset=UTF-8")
                        .body(result);
            }
        } catch (Exception e) {
            System.out.println("로그인 처리 중 오류: " + e.getMessage());
            
            result.put("success", false);
            result.put("message", "로그인 처리 중 오류가 발생했습니다.");
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("Content-Type", "application/json; charset=UTF-8")
                    .body(result);
        }
    }
    
    /**
     * 토큰 검증 API
     * @param tokenRequest 토큰 검증 요청 데이터
     * @return 토큰 검증 결과
     */
    @PostMapping("/validate")
    public ResponseEntity<Map<String, Object>> validateToken(@RequestBody Map<String, String> tokenRequest) {
        System.out.println("=== AuthController.validateToken() 호출됨 ===");
        
        String token = tokenRequest.get("token");
        
        Map<String, Object> result = new HashMap<>();
        
        try {
            if (jwtTokenProvider.validateToken(token)) {
                String username = jwtTokenProvider.getUserName(token);
                System.out.println("토큰 검증 성공: " + username);
                
                result.put("valid", true);
                result.put("username", username);
                result.put("message", "유효한 토큰입니다.");
                
                return ResponseEntity.ok()
                        .header("Content-Type", "application/json; charset=UTF-8")
                        .body(result);
            } else {
                System.out.println("토큰 검증 실패");
                
                result.put("valid", false);
                result.put("message", "유효하지 않은 토큰입니다.");
                
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .header("Content-Type", "application/json; charset=UTF-8")
                        .body(result);
            }
        } catch (Exception e) {
            System.out.println("토큰 검증 중 오류: " + e.getMessage());
            
            result.put("valid", false);
            result.put("message", "토큰 검증 중 오류가 발생했습니다.");
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("Content-Type", "application/json; charset=UTF-8")
                    .body(result);
        }
    }
    
    /**
     * 로그아웃 API
     * @param response HTTP 응답 객체
     * @return 로그아웃 결과
     */
    @PostMapping("/logout")
    public ResponseEntity<Map<String, Object>> logout(HttpServletResponse response) {
        System.out.println("=== AuthController.logout() 호출됨 ===");
        
        // JWT 쿠키 삭제
        Cookie jwtCookie = new Cookie("jwt-token", null);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(false);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(0); // 즉시 삭제
        response.addCookie(jwtCookie);
        System.out.println("JWT 쿠키 삭제됨");
        
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("message", "로그아웃 되었습니다.");
        
        return ResponseEntity.ok()
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(result);
    }
    
    /**
     * 현재 사용자 정보 조회 API
     * @param request HTTP 요청 객체
     * @return 사용자 정보
     */
    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getCurrentUser(@RequestHeader(value = "Authorization", required = false) String authHeader,
                                                             @CookieValue(value = "jwt-token", required = false) String jwtCookie) {
        System.out.println("=== AuthController.getCurrentUser() 호출됨 ===");
        
        Map<String, Object> result = new HashMap<>();
        
        try {
            String token = null;
            
            // Authorization 헤더에서 토큰 추출
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
            }
            // 쿠키에서 토큰 추출
            else if (jwtCookie != null) {
                token = jwtCookie;
            }
            
            if (token != null && jwtTokenProvider.validateToken(token)) {
                String username = jwtTokenProvider.getUserName(token);
                System.out.println("현재 사용자: " + username);
                
                // UserService에서 사용자 정보 조회
                User user = userService.authenticate(username, ""); // 비밀번호는 검증하지 않음
                
                result.put("success", true);
                result.put("username", username);
                result.put("name", user != null ? user.getName() : username);
                
                return ResponseEntity.ok()
                        .header("Content-Type", "application/json; charset=UTF-8")
                        .body(result);
            } else {
                System.out.println("유효한 토큰이 없음");
                
                result.put("success", false);
                result.put("message", "인증되지 않은 사용자입니다.");
                
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .header("Content-Type", "application/json; charset=UTF-8")
                        .body(result);
            }
        } catch (Exception e) {
            System.out.println("사용자 정보 조회 중 오류: " + e.getMessage());
            
            result.put("success", false);
            result.put("message", "사용자 정보 조회 중 오류가 발생했습니다.");
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("Content-Type", "application/json; charset=UTF-8")
                    .body(result);
        }
    }
} 