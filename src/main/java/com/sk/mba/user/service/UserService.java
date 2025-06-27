package com.sk.mba.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sk.mba.user.model.User;
import com.sk.mba.user.repository.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    /**
     * 사용자 인증 메소드
     * @param username 사용자 아이디
     * @param password 비밀번호
     * @return 인증 성공 시 User 객체, 실패 시 null
     */
    public User authenticate(String username, String password) {
        System.out.println("UserService.authenticate 시작");
        System.out.println("=== UserService.authenticate() 호출됨 ===");
        System.out.println("인증 시도 - username: " + username);
        
        try {
            Optional<User> userOpt = userRepository.findByUsernameAndPassword(username, password);
            
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                System.out.println("인증 성공: " + user.getUsername());
                System.out.println("UserService.authenticate 종료");
                return user;
            } else {
                System.out.println("인증 실패: 사용자 정보가 일치하지 않음");
                System.out.println("UserService.authenticate 종료");
                return null;
            }
        } catch (Exception e) {
            System.out.println("인증 처리 중 오류: " + e.getMessage());
            System.out.println("UserService.authenticate 종료");
            return null;
        }
    }
    
    /**
     * 사용자명으로 사용자 조회
     * @param username 사용자명
     * @return 사용자 정보
     */
    public User findByUsername(String username) {
        System.out.println("UserService.findByUsername 시작");
        System.out.println("=== UserService.findByUsername() 호출됨 ===");
        
        try {
            Optional<User> userOpt = userRepository.findByUsername(username);
            if (userOpt.isPresent()) {
                System.out.println("사용자 조회 성공: " + username);
                System.out.println("UserService.findByUsername 종료");
                return userOpt.get();
            } else {
                System.out.println("사용자를 찾을 수 없음: " + username);
                System.out.println("UserService.findByUsername 종료");
                return null;
            }
        } catch (Exception e) {
            System.out.println("사용자 조회 중 오류: " + e.getMessage());
            System.out.println("UserService.findByUsername 종료");
            return null;
        }
    }
    
    /**
     * 사용자 등록
     * @param user 등록할 사용자 정보
     * @return 등록된 사용자 정보
     */
    public User saveUser(User user) {
        System.out.println("UserService.saveUser 시작");
        System.out.println("=== UserService.saveUser() 호출됨 ===");
        
        try {
            // 사용자명 중복 확인
            if (userRepository.existsByUsername(user.getUsername())) {
                System.out.println("사용자명 중복: " + user.getUsername());
                System.out.println("UserService.saveUser 종료");
                return null;
            }
            
            User savedUser = userRepository.save(user);
            System.out.println("사용자 등록 성공: " + savedUser.getUsername());
            System.out.println("UserService.saveUser 종료");
            return savedUser;
        } catch (Exception e) {
            System.out.println("사용자 등록 중 오류: " + e.getMessage());
            System.out.println("UserService.saveUser 종료");
            return null;
        }
    }
    
    /**
     * 모든 사용자 조회
     * @return 사용자 목록
     */
    public List<User> findAllUsers() {
        System.out.println("UserService.findAllUsers 시작");
        System.out.println("=== UserService.findAllUsers() 호출됨 ===");
        
        try {
            List<User> users = userRepository.findAll();
            System.out.println("전체 사용자 수: " + users.size());
            System.out.println("UserService.findAllUsers 종료");
            return users;
        } catch (Exception e) {
            System.out.println("사용자 목록 조회 중 오류: " + e.getMessage());
            System.out.println("UserService.findAllUsers 종료");
            return List.of();
        }
    }
    
    /**
     * 사용자 삭제
     * @param id 사용자 ID
     * @return 삭제 성공 여부
     */
    public boolean deleteUser(Long id) {
        System.out.println("UserService.deleteUser 시작");
        System.out.println("=== UserService.deleteUser() 호출됨 ===");
        
        try {
            if (userRepository.existsById(id)) {
                userRepository.deleteById(id);
                System.out.println("사용자 삭제 성공: ID " + id);
                System.out.println("UserService.deleteUser 종료");
                return true;
            } else {
                System.out.println("삭제할 사용자를 찾을 수 없음: ID " + id);
                System.out.println("UserService.deleteUser 종료");
                return false;
            }
        } catch (Exception e) {
            System.out.println("사용자 삭제 중 오류: " + e.getMessage());
            System.out.println("UserService.deleteUser 종료");
            return false;
        }
    }
} 