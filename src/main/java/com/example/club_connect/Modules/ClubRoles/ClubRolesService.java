package com.example.club_connect.Modules.ClubRoles;

// import com.example.club_connect.Modules.ClubRoles.ClubRolesEntity;
// import com.example.club_connect.Modules.ClubRoles.ClubRolesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import java.util.*;

@Service
public class ClubRolesService {
    
    @Autowired
    private ClubRolesRepository clubRolesRepository;

    public List<ClubRolesEntity> getAllClubRoles() {
        List<ClubRolesEntity> clubRolesList = clubRolesRepository.findAll();
        return clubRolesList;
    }

    public ResponseEntity<ClubRolesEntity> getClubRoleById(Integer id) {
        ClubRolesEntity clubRole = clubRolesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Club role not found with id: " + id));
        return ResponseEntity.ok(clubRole);
    }

    public ResponseEntity<ClubRolesEntity> createClubRole(ClubRolesEntity clubRole) {
        // boolean isExist = clubRolesRepository.existsById(clubRole.getId());
        // if (isExist) {
        //     throw new RuntimeException("Club role already exists with id: " + clubRole.getId());
        // } else {
            ClubRolesEntity createdClubRole = clubRolesRepository.save(clubRole);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdClubRole);
        // }
    }

    public ResponseEntity<ClubRolesEntity> updateClubRole(Integer id, ClubRolesEntity clubRole) {
        ClubRolesEntity existingClubRole = clubRolesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Club role not found with id: " + id));

        if (clubRole.getRoleName() != null) {
            existingClubRole.setRoleName(clubRole.getRoleName());
        }
        
        final ClubRolesEntity updatedClubRole = clubRolesRepository.save(existingClubRole);
        return ResponseEntity.ok(updatedClubRole);
    }

    public ResponseEntity<ClubRolesEntity> deleteClubRole(Integer id) {
        ClubRolesEntity clubRole = clubRolesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Club role not found with id: " + id));
        clubRolesRepository.delete(clubRole);
        return ResponseEntity.ok().build();
    }
}
