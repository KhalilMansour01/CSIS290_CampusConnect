package com.example.campus_connect.Modules.Announcements;

// import com.example.club_connect.Entity.AnnouncementsEntity;
// import com.example.club_connect.Repository.AnnouncementsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import java.util.*;

@Service
public class AnnouncementsService {
    
    @Autowired
    private AnnouncementsRepository announcementsRepository;

    public List<AnnouncementsEntity> getAllAnnouncements() {
        List<AnnouncementsEntity> announcementsList = announcementsRepository.findAll();
        return announcementsList;
    }

    public ResponseEntity<AnnouncementsEntity> getAnnouncementById(Integer id) {
        AnnouncementsEntity announcement = announcementsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Announcement not found with id: " + id));
        return ResponseEntity.ok(announcement);
    }

    public ResponseEntity<AnnouncementsEntity> createAnnouncement(AnnouncementsEntity announcement) {
        boolean isExist = announcementsRepository.existsById(announcement.getId());
        if (isExist) {
            throw new RuntimeException("Announcement already exists with id: " + announcement.getId());
        } else {
            AnnouncementsEntity createdAnnouncement = announcementsRepository.save(announcement);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAnnouncement);
        }
    }

    public ResponseEntity<AnnouncementsEntity> updateAnnouncement(Integer id, AnnouncementsEntity announcement) {
        AnnouncementsEntity existingAnnouncement = announcementsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Announcement not found with id: " + id));

        if (announcement.getClub() != null) {
            existingAnnouncement.setClub(announcement.getClub());
        }
        if (announcement.getMessage() != null) {
            existingAnnouncement.setMessage(announcement.getMessage());
        }
        if (announcement.getPriority() != null) {
            existingAnnouncement.setPriority(announcement.getPriority());
        }
        if(announcement.getExpirationDate() != null) {
            existingAnnouncement.setExpirationDate(announcement.getExpirationDate());
        }

        final AnnouncementsEntity updatedAnnouncement = announcementsRepository.save(existingAnnouncement);
        return ResponseEntity.ok(updatedAnnouncement);
    }

    public ResponseEntity<AnnouncementsEntity> deleteAnnouncement(Integer id) {
        AnnouncementsEntity announcement = announcementsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Announcement not found with id: " + id));
        announcementsRepository.delete(announcement);
        return ResponseEntity.ok().build();
    }
    // public List<AnnouncementsEntity> getAnnouncementsByClubId(Integer clubId) {
    //     List<AnnouncementsEntity> announcementsList = announcementsRepository.findByClubId(clubId);
    //     return announcementsList;
    // }

}
