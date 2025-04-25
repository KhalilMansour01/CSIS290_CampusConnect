package com.example.campus_connect.Modules.Announcements;

// import com.example.club_connect.Entity.AnnouncementsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementsRepository extends JpaRepository<AnnouncementsEntity, Integer> {
    // AnnouncementsEntity findByAnnouncementId(String announcementId);    

    
}
