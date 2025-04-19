package com.example.club_connect.Modules.MembershipRequests;


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
@RequestMapping("/membership-requests")
public class MembershipRequestsController {

    @Autowired
    private MembershipRequestsService membershipRequestsService;

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
            @RequestBody MembershipRequestsEntity membershipRequest) {
        return membershipRequestsService.createMembershipRequest(membershipRequest);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MembershipRequestsEntity> updateMembershipRequest(@PathVariable Integer id,
            @RequestBody MembershipRequestsEntity membershipRequest) {
        return membershipRequestsService.updateMembershipRequest(id, membershipRequest);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MembershipRequestsEntity> deleteMembershipRequest(@PathVariable Integer id) {
        return membershipRequestsService.deleteMembershipRequest(id);
    }
}
