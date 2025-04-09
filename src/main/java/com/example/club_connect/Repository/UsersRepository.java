package com.example.club_connect.Repository;

import com.example.club_connect.Entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersEntity, String> {
    
}
