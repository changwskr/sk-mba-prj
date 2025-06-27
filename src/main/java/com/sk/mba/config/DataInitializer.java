package com.sk.mba.config;

import com.sk.mba.user.model.User;
import com.sk.mba.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public void run(String... args) throws Exception {
        System.out.println("DataInitializer.run 시작");
        System.out.println("=== DataInitializer.run() 호출됨 ===");
        
        // 기존 데이터 확인
        if (userRepository.count() == 0) {
            System.out.println("초기 사용자 데이터 생성 시작");
            
            // 테스트 사용자 생성
            User user1 = new User("user", "password", "테스트 사용자");
            User user2 = new User("admin", "admin123", "관리자");
            User user3 = new User("test", "test123", "테스트 계정");
            
            // 데이터베이스에 저장
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
            
            System.out.println("초기 사용자 데이터 생성 완료");
            System.out.println("생성된 사용자 수: " + userRepository.count());
        } else {
            System.out.println("기존 사용자 데이터가 존재합니다. 초기화를 건너뜁니다.");
            System.out.println("현재 사용자 수: " + userRepository.count());
        }
        System.out.println("DataInitializer.run 종료");
    }
} 