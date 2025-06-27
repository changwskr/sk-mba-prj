package com.sk.mba.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.sk.mba.user.service.UserService;
import com.sk.mba.user.model.User;
import com.sk.mba.config.JwtTokenProvider;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    /**
     * 로그인 페이지 표시 (GET)
     */
    @GetMapping("/user/login")
    public String loginForm() {
        System.out.println("=== LoginController.loginForm() 호출됨 ===");
        return "user/login";
    }
    
    /**
     * 로그인 처리 (POST)
     */
    @PostMapping("/user/login")
    public String login(@RequestParam String username, 
                       @RequestParam String password,
                       HttpSession session,
                       HttpServletResponse response,
                       Model model) {
        
        System.out.println("=== LoginController.login() 호출됨 ===");
        System.out.println("입력된 username: " + username);
        System.out.println("입력된 password: " + password);
        
        // UserService를 통한 인증
        User authenticatedUser = userService.authenticate(username, password);
        
        if (authenticatedUser != null) {
            System.out.println("로그인 성공: " + authenticatedUser.getUsername());
            
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
            
            // 세션에도 사용자 정보 저장 (기존 호환성 유지)
            session.setAttribute("loginUser", authenticatedUser);
            session.setAttribute("username", authenticatedUser.getUsername());
            session.setAttribute("name", authenticatedUser.getName());
            
            return "redirect:/home";
        } else {
            System.out.println("로그인 실패: 인증되지 않은 사용자");
            // 로그인 실패
            model.addAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "user/login";
        }
    }
    
    /**
     * 홈 페이지
     */
    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        System.out.println("=== LoginController.home() 호출됨 ===");
        
        // 세션에서 사용자 정보 확인
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            System.out.println("세션에 로그인 정보 없음 - 로그인 페이지로 리다이렉트");
            return "redirect:/user/login";
        }
        
        System.out.println("홈 페이지 접근: " + loginUser.getUsername());
        model.addAttribute("username", loginUser.getUsername());
        model.addAttribute("name", loginUser.getName());
        return "user/home";
    }
    
    /**
     * 로그아웃 처리
     */
    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response, Model model) {
        System.out.println("=== LoginController.logout() 호출됨 ===");
        
        // 세션에서 사용자 정보 확인
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser != null) {
            System.out.println("로그아웃: " + loginUser.getUsername());
        } else {
            System.out.println("로그아웃: 세션에 사용자 정보 없음");
        }
        
        // JWT 쿠키 삭제
        Cookie jwtCookie = new Cookie("jwt-token", null);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(false);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(0); // 즉시 삭제
        response.addCookie(jwtCookie);
        System.out.println("JWT 쿠키 삭제됨");
        
        // 세션 무효화
        session.invalidate();
        System.out.println("세션 무효화 완료");
        
        model.addAttribute("message", "로그아웃 되었습니다.");
        return "redirect:/user/login";
    }
} 