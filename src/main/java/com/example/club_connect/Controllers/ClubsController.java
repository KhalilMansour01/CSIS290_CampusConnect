package com.example.club_connect.Controllers;

import com.example.club_connect.Service.ClubsService;
import com.example.club_connect.Entity.ClubsEntity;

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

@Controller
@RequestMapping("/clubs")
public class ClubsController {

    @Autowired
    private ClubsService clubsService;

    @GetMapping("/all")
    public ResponseEntity<List<ClubsEntity>> getAllClubs() {
        List<ClubsEntity> clubs = clubsService.getAllClubs();
        return new ResponseEntity<>(clubs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubsEntity> getClubById(@PathVariable Integer id) {
        return clubsService.getClubById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<ClubsEntity> createClub(@RequestBody ClubsEntity club) {
        return clubsService.createClub(club);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ClubsEntity> updateClub(@PathVariable Integer id, @RequestBody ClubsEntity club) {
        return clubsService.updateClub(id, club);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ClubsEntity> deleteClub(@PathVariable Integer id) {
        return clubsService.deleteClub(id);
    }
}
