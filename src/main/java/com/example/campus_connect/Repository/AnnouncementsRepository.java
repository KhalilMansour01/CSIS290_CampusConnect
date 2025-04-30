package com.example.campus_connect.Repository;

// import com.example.club_connect.Entity.AnnouncementsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.campus_connect.Entity.AnnouncementsEntity;

public interface AnnouncementsRepository extends JpaRepository<AnnouncementsEntity, Integer> {
    // AnnouncementsEntity findByAnnouncementId(String announcementId);    

    
}
