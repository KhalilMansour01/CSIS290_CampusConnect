package com.example.campus_connect.Service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

// import com.example.club_connect.Modules.Users.UserResponseDTO;
// import com.example.club_connect.Modules.Users.UsersEntity;
// import com.example.club_connect.Modules.Users.UsersRepository;

import java.util.*;
import java.util.stream.Collectors;

import com.example.campus_connect.Repository.ClubsRepository;
import com.example.campus_connect.DTOs.Clubs.ClubRequestDTO;
import com.example.campus_connect.DTOs.Clubs.ClubResponseDTO;
import com.example.campus_connect.Entity.ClubsEntity;
import com.example.campus_connect.Mapper.ClubMapper;

@Service
public class ClubsService {

    @Autowired
    private ClubsRepository clubsRepository;

    // @Autowired
    // private UsersRepository usersRepository;

    @Autowired
    private ClubMapper clubMapper;

    public List<ClubResponseDTO> getAllClubs() {
        return clubsRepository.findAll().stream()
                .map(clubMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<ClubResponseDTO> getClubById(Integer id) {
        ClubsEntity club = clubsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Club not found with id: " + id));
        return ResponseEntity.ok(clubMapper.toResponseDto(club));
    }

    public ResponseEntity<ClubResponseDTO> createClub(ClubRequestDTO clubRequestDto) {
        ClubsEntity club = clubMapper.toEntity(clubRequestDto); // uses @Named("mapPresident") internally
        ClubsEntity createdClub = clubsRepository.save(club);
        ClubResponseDTO responseDto = clubMapper.toResponseDto(createdClub);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
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
