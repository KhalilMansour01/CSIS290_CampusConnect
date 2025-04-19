package com.example.club_connect.Modules.MembershipRequests;    

// import com.example.club_connect.Entity.MembershipRequestsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRequestsRepository extends JpaRepository<MembershipRequestsEntity, Integer> {
    // MembershipRequestsEntity findByUserIdAndClubId(String userId, String clubId);
    
    
}
