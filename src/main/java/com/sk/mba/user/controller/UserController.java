package com.sk.mba.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.sk.mba.user.model.User;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
    @GetMapping("/user/profile")
    public String profile(HttpSession session, Model model) {
        System.out.println("=== UserController.profile() 호출됨 ===");
        
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            System.out.println("세션에 로그인 정보 없음 - 로그인 페이지로 리다이렉트");
            return "redirect:/user/login";
        }
        
        System.out.println("프로필 페이지 접근: " + loginUser.getUsername());
        model.addAttribute("username", loginUser.getUsername());
        model.addAttribute("name", loginUser.getName());
        return "user/profile";
    }
} 