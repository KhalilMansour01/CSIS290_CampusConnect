package com.example.campus_connect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

import com.example.campus_connect.Entity.ClubsEntity;
import com.example.campus_connect.Service.ClubsService;

@Controller
@RequestMapping("/api/clubs")
public class ClubsController {

    @Autowired
    private ClubsService clubsService;

    @GetMapping("/all")
    public ResponseEntity<List<ClubsEntity>> getAllClubs() {
        List<ClubsEntity> response = clubsService.getAllClubs();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubsEntity> getClubById(@PathVariable Integer id) {
        ClubsEntity response = clubsService.getClubById(id).getBody();
        return new ResponseEntity<>(response, HttpStatus.OK);
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
