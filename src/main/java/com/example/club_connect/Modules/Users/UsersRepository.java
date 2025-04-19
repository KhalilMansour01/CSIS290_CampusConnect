package com.example.club_connect.Modules.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersEntity, String> {
    
}
