package com.example.campus_connect.Repository;

// import com.example.club_connect.Modules.ClubRoles.ClubRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.campus_connect.Entity.ClubRolesEntity;

public interface ClubRolesRepository extends JpaRepository<ClubRolesEntity, Integer> {
    // ClubRolesEntity findByRoleId(String roleId);

    
} 