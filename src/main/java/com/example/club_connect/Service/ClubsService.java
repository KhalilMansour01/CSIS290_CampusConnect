package com.example.club_connect.Service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.example.club_connect.Entity.ClubsEntity;
import com.example.club_connect.Repository.ClubsRepository;

import java.util.*;


@Service
public class ClubsService {
    
    @Autowired
    private ClubsRepository clubsRepository;

    public List<ClubsEntity> getAllClubs() {
        List<ClubsEntity> clubsList = clubsRepository.findAll();
        return clubsList;
    }

    public ResponseEntity<ClubsEntity> getClubById(Integer id) {
        ClubsEntity club = clubsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Club not found with id: " + id));
        return ResponseEntity.ok(club);
    }

    public ResponseEntity<ClubsEntity> createClub(ClubsEntity club) {
        boolean isExist = clubsRepository.existsById(club.getId());
        if (isExist) {
            throw new RuntimeException("Club already exists with id: " + club.getId());
        } else {
            ClubsEntity createdClub = clubsRepository.save(club);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdClub);
        }
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
