package com.sk.mba.user.repository;

import com.sk.mba.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * 사용자명으로 사용자 조회
     * @param username 사용자명
     * @return 사용자 정보 (Optional)
     */
    Optional<User> findByUsername(String username);
    
    /**
     * 사용자명과 비밀번호로 사용자 조회
     * @param username 사용자명
     * @param password 비밀번호
     * @return 사용자 정보 (Optional)
     */
    Optional<User> findByUsernameAndPassword(String username, String password);
    
    /**
     * 사용자명 존재 여부 확인
     * @param username 사용자명
     * @return 존재 여부
     */
    boolean existsByUsername(String username);
    
    /**
     * 사용자명으로 사용자명 조회 (로그인용)
     * @param username 사용자명
     * @return 사용자명
     */
    @Query("SELECT u.username FROM User u WHERE u.username = :username")
    Optional<String> findUsernameByUsername(@Param("username") String username);
} 