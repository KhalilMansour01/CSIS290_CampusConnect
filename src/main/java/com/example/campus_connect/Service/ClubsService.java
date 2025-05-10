package com.example.campus_connect.Service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.*;
import java.util.stream.Collectors;

import com.example.campus_connect.Repository.ClubsRepository;
import com.example.campus_connect.Entity.ClubsEntity;

@Service
public class ClubsService {

    @Autowired
    private ClubsRepository clubsRepository;


    public List<ClubsEntity> getAllClubs() {
        return clubsRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    public ResponseEntity<ClubsEntity> getClubById(Integer id) {
        ClubsEntity club = clubsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Club not found with id: " + id));
        return ResponseEntity.ok(club);
    }

    public ResponseEntity<ClubsEntity> createClub(ClubsEntity clubsEntity) {
        ClubsEntity createdClub = clubsRepository.save(clubsEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClub);
    }

    public ResponseEntity<ClubsEntity> updateClub(Integer id, ClubsEntity club) {
        ClubsEntity existingClub = clubsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Club not found with id: " + id));

        if (club.getName() != null) {
            existingClub.setName(club.getName());
        }
        if (club.getDescription() != null) {
            existingClub.setDescription(club.getDescription());
        }
        if (club.getCategory() != null) {
            existingClub.setCategory(club.getCategory());
        }
        if (club.getStatus() != null) {
            existingClub.setStatus(club.getStatus());
        }
        if (club.getPresident() != null) {
            existingClub.setPresident(club.getPresident());
        }

        final ClubsEntity updatedClub = clubsRepository.save(existingClub);
        return ResponseEntity.ok(updatedClub);
    }

    public ResponseEntity<ClubsEntity> deleteClub(Integer id) {
        ClubsEntity club = clubsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Club not found with id: " + id));
        clubsRepository.delete(club);
        return ResponseEntity.ok().build();
    }

}
