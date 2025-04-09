package com.example.club_connect.Repository;

import com.example.club_connect.Entity.ClubMembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubMembershipRepository extends JpaRepository<ClubMembershipEntity, Integer> {
    // ClubMembershipEntity findByUserIdAndClubId(String userId, String clubId);

    
}
