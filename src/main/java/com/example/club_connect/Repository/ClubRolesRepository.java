package com.example.club_connect.Repository;

import com.example.club_connect.Entity.ClubRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRolesRepository extends JpaRepository<ClubRolesEntity, Integer> {
    // ClubRolesEntity findByRoleId(String roleId);

    
} 