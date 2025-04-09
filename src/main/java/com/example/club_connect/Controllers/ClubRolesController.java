package com.example.club_connect.Controllers;

import com.example.club_connect.Service.ClubRolesService;
import com.example.club_connect.Entity.ClubRolesEntity;

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
@RequestMapping("/club-roles")
public class ClubRolesController {

    @Autowired
    private ClubRolesService clubRolesService;

    @GetMapping("/all")
    public ResponseEntity<List<ClubRolesEntity>> getAllClubRoles() {
        List<ClubRolesEntity> clubRoles = clubRolesService.getAllClubRoles();
        return new ResponseEntity<>(clubRoles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubRolesEntity> getClubRoleById(@PathVariable Integer id) {
        return clubRolesService.getClubRoleById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<ClubRolesEntity> createClubRole(@RequestBody ClubRolesEntity clubRole) {
        return clubRolesService.createClubRole(clubRole);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ClubRolesEntity> updateClubRole(@PathVariable Integer id,
            @RequestBody ClubRolesEntity clubRole) {
        return clubRolesService.updateClubRole(id, clubRole);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ClubRolesEntity> deleteClubRole(@PathVariable Integer id) {
        return clubRolesService.deleteClubRole(id);
    }
}
