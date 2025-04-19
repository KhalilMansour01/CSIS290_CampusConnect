package com.example.club_connect.Modules.Events;

// import com.example.club_connect.Entity.EventsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<EventsEntity, Integer> {
    // EventsEntity findByEventId(String eventId);
    
}
