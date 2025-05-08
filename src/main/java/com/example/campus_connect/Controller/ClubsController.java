package com.example.campus_connect.Controller;

// import com.example.club_connect.Modules.Clubs.ClubsService;
// import com.example.club_connect.Modules.Clubs.ClubDTO;
// import com.example.club_connect.Modules.Clubs.ClubResponseDTO;
// import com.example.club_connect.Modules.Clubs.ClubsEntity;

import org.springframework.security.access.prepost.PreAuthorize;
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

import com.example.campus_connect.DTOs.Clubs.ClubRequestDTO;
import com.example.campus_connect.DTOs.Clubs.ClubResponseDTO;
import com.example.campus_connect.Entity.ClubsEntity;
import com.example.campus_connect.Service.ClubsService;

@Controller
@RequestMapping("/clubs")
public class ClubsController {

    @Autowired
    private ClubsService clubsService;

    

    @GetMapping("/all")
    public ResponseEntity<List<ClubResponseDTO>> getAllClubs() {
        List<ClubResponseDTO> response = clubsService.getAllClubs();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubResponseDTO> getClubById(@PathVariable Integer id) {
        ClubResponseDTO response = clubsService.getClubById(id).getBody();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_OSA_ADMIN')")
    public ResponseEntity<ClubResponseDTO> createClub(@RequestBody ClubRequestDTO clubDto) {
        return clubsService.createClub(clubDto);
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
