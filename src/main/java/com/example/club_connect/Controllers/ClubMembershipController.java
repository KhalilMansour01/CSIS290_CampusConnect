package com.example.club_connect.Controllers;

import com.example.club_connect.Service.ClubMembershipService;
import com.example.club_connect.Entity.ClubMembershipEntity;

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
@RequestMapping("/club-membership")
public class ClubMembershipController {

    @Autowired
    private ClubMembershipService clubMembershipService;

    @GetMapping("/all")
    public ResponseEntity<List<ClubMembershipEntity>> getAllClubMemberships() {
        List<ClubMembershipEntity> clubMemberships = clubMembershipService.getAllClubMemberships();
        return new ResponseEntity<>(clubMemberships, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubMembershipEntity> getClubMembershipById(@PathVariable Integer id) {
        return clubMembershipService.getClubMembershipById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<ClubMembershipEntity> createClubMembership(@RequestBody ClubMembershipEntity clubMembership) {
        return clubMembershipService.createClubMembership(clubMembership);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ClubMembershipEntity> updateClubMembership(@PathVariable Integer id,
            @RequestBody ClubMembershipEntity clubMembership) {
        return clubMembershipService.updateClubMembership(id, clubMembership);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ClubMembershipEntity> deleteClubMembership(@PathVariable Integer id) {
        return clubMembershipService.deleteClubMembership(id);
    }
}
