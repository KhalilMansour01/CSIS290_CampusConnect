package com.example.campus_connect.Repository;

// import com.example.club_connect.Entity.EventAttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.campus_connect.Entity.EventAttendanceEntity;

public interface EventAttendanceRepository extends JpaRepository<EventAttendanceEntity, Integer> {
    boolean existsByEventIdAndUserId(Integer eventId, String userId);
    
    
}
