package com.example.campus_connect.Repository;

// import com.example.club_connect.Entity.EventsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.campus_connect.Entity.EventsEntity;

public interface EventsRepository extends JpaRepository<EventsEntity, Integer> {
    // EventsEntity findByEventId(String eventId);
    
}
