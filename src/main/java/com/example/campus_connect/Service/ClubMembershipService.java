package com.example.campus_connect.Service;

// import com.example.club_connect.Entity.ClubMembershipEntity;
// import com.example.club_connect.Repository.ClubMembershipRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import java.util.*;
import java.util.stream.Collectors;

import com.example.campus_connect.Entity.ClubMembershipEntity;
import com.example.campus_connect.Entity.UsersEntity;
import com.example.campus_connect.Repository.ClubMembershipRepository;

@Service
public class ClubMembershipService {

    @Autowired
    private ClubMembershipRepository clubMembershipRepository;

    public List<ClubMembershipEntity> getAllClubMemberships() {
        List<ClubMembershipEntity> clubMembershipList = clubMembershipRepository.findAll();
        return clubMembershipList;
    }

    public ResponseEntity<ClubMembershipEntity> getClubMembershipById(Integer id) {
        ClubMembershipEntity clubMembership = clubMembershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Club membership not found with id: " + id));
        return ResponseEntity.ok(clubMembership);
    }

    public ResponseEntity<ClubMembershipEntity> createClubMembership(ClubMembershipEntity clubMembership) {
        ClubMembershipEntity createdClubMembership = clubMembershipRepository.save(clubMembership);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClubMembership);
    }

    public ResponseEntity<ClubMembershipEntity> updateClubMembership(Integer id, ClubMembershipEntity clubMembership) {
        ClubMembershipEntity existingClubMembership = clubMembershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Club membership not found with id: " + id));

        if (clubMembership.getUser() != null) {
            existingClubMembership.setUser(clubMembership.getUser());
        }
        if (clubMembership.getClub() != null) {
            existingClubMembership.setClub(clubMembership.getClub());
        }
        if (clubMembership.getRole() != null) {
            existingClubMembership.setRole(clubMembership.getRole());
        }

        final ClubMembershipEntity updatedClubMembership = clubMembershipRepository.save(existingClubMembership);
        return ResponseEntity.ok(updatedClubMembership);
    }

    public ResponseEntity<ClubMembershipEntity> deleteClubMembership(Integer id) {
        ClubMembershipEntity clubMembership = clubMembershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Club membership not found with id: " + id));
        clubMembershipRepository.delete(clubMembership);
        return ResponseEntity.ok().build();
    }

    public List<UsersEntity> getMembersByClubId(Integer clubId) {
        List<ClubMembershipEntity> memberships = clubMembershipRepository.findByClubId(clubId);
        return memberships.stream()
                .map(ClubMembershipEntity::getUser)
                .collect(Collectors.toList());
    }

}
