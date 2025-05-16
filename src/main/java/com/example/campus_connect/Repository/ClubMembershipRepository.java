package com.example.campus_connect.Repository;

// import com.example.club_connect.Entity.ClubMembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.campus_connect.Entity.ClubMembershipEntity;
import java.util.List;

public interface ClubMembershipRepository extends JpaRepository<ClubMembershipEntity, Integer> {
    // ClubMembershipEntity findByUserIdAndClubId(String userId, String clubId);
    List<ClubMembershipEntity> findByClubId(Integer clubId);

    
}
