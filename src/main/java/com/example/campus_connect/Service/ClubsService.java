package com.example.campus_connect.Service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.*;
import java.util.stream.Collectors;

import com.example.campus_connect.Repository.ClubsRepository;
import com.example.campus_connect.Repository.UsersRepository;
import com.example.campus_connect.Entity.ClubMembershipEntity;
import com.example.campus_connect.Entity.ClubRolesEntity;
import com.example.campus_connect.Entity.ClubsEntity;
import com.example.campus_connect.Entity.UsersEntity;
import com.example.campus_connect.Repository.ClubMembershipRepository;
import com.example.campus_connect.Repository.ClubRolesRepository;

@Service
public class ClubsService {

    @Autowired
    private ClubsRepository clubsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ClubMembershipRepository clubMembershipRepository;

    @Autowired
    private ClubRolesRepository clubRolesRepository;

    public List<ClubsEntity> getAllClubs() {
        return clubsRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    public ResponseEntity<ClubsEntity> getClubById(Integer id) {
        ClubsEntity club = clubsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Club not found with id: " + id));
        return ResponseEntity.ok(club);
    }

    public ResponseEntity<ClubsEntity> getByPresidentId(String presidentId) {
        ClubsEntity club = clubsRepository.findByPresidentId(presidentId)
                .orElseThrow(() -> new RuntimeException("Club not found with president id: " + presidentId));
        return ResponseEntity.ok(club);
    }

    public ResponseEntity<ClubsEntity> createClub(ClubsEntity clubsEntity) {

        // Save the club entity
        ClubsEntity newClub = clubsRepository.save(clubsEntity);

        // Create a new ClubMembershipEntity for the president
        ClubMembershipEntity clubMembership = new ClubMembershipEntity();
        // Set the president as the user in the club membership
        UsersEntity president = usersRepository.findById(clubsEntity.getPresident().getId())
                .orElseThrow(
                        () -> new RuntimeException("User not found with id: " + clubsEntity.getPresident().getId()));
        ClubRolesEntity presidentRole = clubRolesRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("President club role not found"));
        // Set the club membership details
        clubMembership.setUser(president);
        clubMembership.setClub(newClub);
        clubMembership.setRole(presidentRole);
        // Save the club membership
        clubMembershipRepository.save(clubMembership);

        // TODO Change the user type to officer
        // president.setUserType("Officer");
        // usersRepository.save(president);

        return ResponseEntity.status(HttpStatus.CREATED).body(clubsEntity);
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
