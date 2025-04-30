package com.example.campus_connect.Repository;

// import com.example.club_connect.Entity.MembershipRequestsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.campus_connect.Entity.MembershipRequestsEntity;

public interface MembershipRequestsRepository extends JpaRepository<MembershipRequestsEntity, Integer> {
    // MembershipRequestsEntity findByUserIdAndClubId(String userId, String clubId);
    
    
}
