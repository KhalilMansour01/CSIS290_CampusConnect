package com.example.campus_connect.Controller;


import com.example.campus_connect.Entity.ClubsEntity;
import com.example.campus_connect.Entity.UsersEntity;
import com.example.campus_connect.Service.ClubsService;
import com.example.campus_connect.Service.UsersService;
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

import java.time.LocalDate;
import java.util.*;

import com.example.campus_connect.Entity.MembershipRequestsEntity;
import com.example.campus_connect.Service.MembershipRequestsService;

@Controller
@RequestMapping("/api/membership-requests")
public class MembershipRequestsController {

    @Autowired
    private MembershipRequestsService membershipRequestsService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ClubsService clubsService;

    @GetMapping("/all")
    public ResponseEntity<List<MembershipRequestsEntity>> getAllMembershipRequests() {
        List<MembershipRequestsEntity> membershipRequests = membershipRequestsService.getAllMembershipRequests();
        return new ResponseEntity<>(membershipRequests, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembershipRequestsEntity> getMembershipRequestById(@PathVariable Integer id) {
        return membershipRequestsService.getMembershipRequestById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<MembershipRequestsEntity> createMembershipRequest(
            @RequestBody Map<String, Object> request) {

        String userId = (String) request.get("userId");
        Integer clubId = (Integer) request.get("clubId");

        // Fetch user and club from the database using userId and clubId
        UsersEntity user = usersService.getUserById(userId).getBody(); // Assuming usersService has this method
        ClubsEntity club = clubsService.getClubById(clubId).getBody(); // Assuming clubsService has this method

        if (user == null || club == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Bad request if user or club not found
        }

        // Create the membership request and set the user and club
        MembershipRequestsEntity membershipRequest = new MembershipRequestsEntity();
        membershipRequest.setUser(user);
        membershipRequest.setClub(club);
        membershipRequest.setStatus("Pending");

        // Save the membership request and return the response
        return membershipRequestsService.createMembershipRequest(membershipRequest);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<MembershipRequestsEntity> updateMembershipRequest(@PathVariable Integer id,
                                                                            @RequestBody Map<String, Object> request) {

        // Fetch the existing membership request by ID
        MembershipRequestsEntity existingRequest = membershipRequestsService.getMembershipRequestById(id).getBody();
        if (existingRequest == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Not found if the request doesn't exist
        }

        // Fetch user and club by IDs
        String userId = (String) request.get("userId");
        Integer clubId = (Integer) request.get("clubId");

        UsersEntity user = usersService.getUserById(userId).getBody();  // Assuming usersService has this method
        ClubsEntity club = clubsService.getClubById(clubId).getBody();  // Assuming clubsService has this method

        if (user == null || club == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Bad request if user or club is invalid
        }

        // Update the membership request
        existingRequest.setUser(user);
        existingRequest.setClub(club);
        existingRequest.setStatus((String) request.get("status"));  // Assume status is passed in the request body
        existingRequest.setRequestDate(LocalDate.now());  // Optionally, update the date to current time

        // Call the service to save the updated entity
        MembershipRequestsEntity updatedRequest = membershipRequestsService.updateMembershipRequest(id, existingRequest).getBody();

        // Return the updated request
        return new ResponseEntity<>(updatedRequest, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MembershipRequestsEntity> deleteMembershipRequest(@PathVariable Integer id) {
        return membershipRequestsService.deleteMembershipRequest(id);
    }
}
