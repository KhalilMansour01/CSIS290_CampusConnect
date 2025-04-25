package com.example.campus_connect.Modules.Users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersEntity, String> {
    Optional<UsersEntity> findByEmail(String email);
}
