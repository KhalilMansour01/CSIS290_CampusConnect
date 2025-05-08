package com.example.campus_connect.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.campus_connect.Entity.UsersEntity;

public interface UsersRepository extends JpaRepository<UsersEntity, String> {
    // Find user by email
    Optional<UsersEntity> findByEmail(String email);
    
    // Check if email exists
    boolean existsByEmail(String email);

    // Find user by verification token
    Optional<UsersEntity> findByVerificationToken(String verificationToken);
}
