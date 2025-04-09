package com.example.club_connect.Repository;

import com.example.club_connect.Entity.EventAttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventAttendanceRepository extends JpaRepository<EventAttendanceEntity, Integer> {
    // EventAttendanceEntity findByEventIdAndUserId(String eventId, String userId);
    
    
}
