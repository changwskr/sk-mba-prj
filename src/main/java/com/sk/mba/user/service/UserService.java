package com.sk.mba.user.service;

import org.springframework.stereotype.Service;
import com.sk.mba.user.model.User;
import java.util.Map;
import java.util.HashMap;

@Service
public class UserService {
    
    // 사용자 정보를 저장하는 static Map
    private static final Map<String, User> USERS = new HashMap<>();
    
    // 초기 사용자 데이터 설정
    static {
        User loginUser1 = new User("user", "password", "테스트 사용자");
        User loginUser2 = new User("admin", "admin123", "관리자");
        User loginUser3 = new User("test", "test123", "테스트 계정");
        
        USERS.put(loginUser1.getUsername(), loginUser1);
        USERS.put(loginUser2.getUsername(), loginUser2);
        USERS.put(loginUser3.getUsername(), loginUser3);
    }
    
    /**
     * 사용자 인증 메소드
     * @param username 사용자 아이디
     * @param password 비밀번호
     * @return 인증 성공 시 User 객체, 실패 시 null
     */
    public User authenticate(String username, String password) {
        User loginUser = USERS.get(username);
        if (loginUser != null && loginUser.getPassword().equals(password)) {
            return loginUser;
        }
        return null; // 인증 실패
    }
} 