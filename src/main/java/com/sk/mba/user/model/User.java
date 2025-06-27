package com.sk.mba.user.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false, length = 50)
    private String username;
    
    @Column(nullable = false, length = 100)
    private String password;
    
    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // 기본 생성자
    public User() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        System.out.println("User.User 시작");
        System.out.println("User.User 종료");
    }
    
    // 생성자
    public User(String username, String password, String name) {
        this();
        System.out.println("User.User(String,String,String) 시작");
        this.username = username;
        this.password = password;
        this.name = name;
        System.out.println("User.User(String,String,String) 종료");
    }
    
    // Getter와 Setter
    public Long getId() {
        System.out.println("User.getId 시작");
        System.out.println("User.getId 종료");
        return id;
    }
    
    public void setId(Long id) {
        System.out.println("User.setId 시작");
        this.id = id;
        System.out.println("User.setId 종료");
    }
    
    public String getUsername() {
        System.out.println("User.getUsername 시작");
        System.out.println("User.getUsername 종료");
        return username;
    }
    
    public void setUsername(String username) {
        System.out.println("User.setUsername 시작");
        this.username = username;
        System.out.println("User.setUsername 종료");
    }
    
    public String getPassword() {
        System.out.println("User.getPassword 시작");
        System.out.println("User.getPassword 종료");
        return password;
    }
    
    public void setPassword(String password) {
        System.out.println("User.setPassword 시작");
        this.password = password;
        System.out.println("User.setPassword 종료");
    }
    
    public String getName() {
        System.out.println("User.getName 시작");
        System.out.println("User.getName 종료");
        return name;
    }
    
    public void setName(String name) {
        System.out.println("User.setName 시작");
        this.name = name;
        System.out.println("User.setName 종료");
    }
    
    public LocalDateTime getCreatedAt() {
        System.out.println("User.getCreatedAt 시작");
        System.out.println("User.getCreatedAt 종료");
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        System.out.println("User.setCreatedAt 시작");
        this.createdAt = createdAt;
        System.out.println("User.setCreatedAt 종료");
    }
    
    public LocalDateTime getUpdatedAt() {
        System.out.println("User.getUpdatedAt 시작");
        System.out.println("User.getUpdatedAt 종료");
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        System.out.println("User.setUpdatedAt 시작");
        this.updatedAt = updatedAt;
        System.out.println("User.setUpdatedAt 종료");
    }
    
    @PreUpdate
    public void preUpdate() {
        System.out.println("User.preUpdate 시작");
        this.updatedAt = LocalDateTime.now();
        System.out.println("User.preUpdate 종료");
    }
} 