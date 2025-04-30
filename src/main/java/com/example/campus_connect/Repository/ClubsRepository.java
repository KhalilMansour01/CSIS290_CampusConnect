package com.example.campus_connect.Repository;

// import com.example.club_connect.Modules.Clubs.ClubsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.campus_connect.Entity.ClubsEntity;

public interface ClubsRepository extends JpaRepository<ClubsEntity, Integer> {
    // ClubsEntity findByClubId(String clubId);
    // ClubsEntity findByClubName(String clubName);
    // ClubsEntity findByClubDescription(String clubDescription);
    // ClubsEntity findByClubType(String clubType);
    // ClubsEntity findByClubCategory(String clubCategory);

}
