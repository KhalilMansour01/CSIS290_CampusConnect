package com.example.campus_connect.Repository;

import com.example.campus_connect.Entity.UserIdCounter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserIdCounterRepository extends JpaRepository<UserIdCounter, String> {
    
}
