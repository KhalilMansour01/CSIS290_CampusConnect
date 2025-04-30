package com.example.campus_connect.Controller;

// import com.example.club_connect.Service.AnnouncementsService;
// import com.example.club_connect.Entity.AnnouncementsEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
// import org.springframework.data.domain.PageRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestParam;

// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageImpl;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.Sort;

import java.util.*;

import com.example.campus_connect.Entity.AnnouncementsEntity;
import com.example.campus_connect.Service.AnnouncementsService;

@Controller
@RequestMapping("/announcements")
public class AnnouncementsController {

    @Autowired
    private AnnouncementsService announcementsService;

    @GetMapping("/all")
    public ResponseEntity<List<AnnouncementsEntity>> getAllAnnouncements() {
        List<AnnouncementsEntity> announcements = announcementsService.getAllAnnouncements();
        return new ResponseEntity<>(announcements, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnnouncementsEntity> getAnnouncementById(@PathVariable Integer id) {
        return announcementsService.getAnnouncementById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<AnnouncementsEntity> createAnnouncement(@RequestBody AnnouncementsEntity announcement) {
        return announcementsService.createAnnouncement(announcement);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AnnouncementsEntity> updateAnnouncement(@PathVariable Integer id,
            @RequestBody AnnouncementsEntity announcement) {
        return announcementsService.updateAnnouncement(id, announcement);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AnnouncementsEntity> deleteAnnouncement(@PathVariable Integer id) {
        return announcementsService.deleteAnnouncement(id);
    }
}
