package com.example.campus_connect.Modules.ClubRoles;

// import com.example.club_connect.Modules.ClubRoles.ClubRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRolesRepository extends JpaRepository<ClubRolesEntity, Integer> {
    // ClubRolesEntity findByRoleId(String roleId);

    
} 