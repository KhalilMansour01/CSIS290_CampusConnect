package com.example.campus_connect.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.campus_connect.Entity.UsersEntity;

public interface UsersRepository extends JpaRepository<UsersEntity, String> {
    Optional<UsersEntity> findByEmail(String email);
}
