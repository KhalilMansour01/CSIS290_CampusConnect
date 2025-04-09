package com.example.club_connect.Repository;

import com.example.club_connect.Entity.ClubsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubsRepository extends JpaRepository<ClubsEntity, Integer> {
    // ClubsEntity findByClubId(String clubId);
    // ClubsEntity findByClubName(String clubName);
    // ClubsEntity findByClubDescription(String clubDescription);
    // ClubsEntity findByClubType(String clubType);
    // ClubsEntity findByClubCategory(String clubCategory);
    
}
