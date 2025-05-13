package com.example.campus_connect.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.campus_connect.Entity.ClubMembershipEntity;
import com.example.campus_connect.Entity.ClubRolesEntity;
import com.example.campus_connect.Entity.MembershipRequestsEntity;
import com.example.campus_connect.Repository.ClubMembershipRepository;
import com.example.campus_connect.Repository.MembershipRequestsRepository;
import com.example.campus_connect.Repository.ClubRolesRepository;

@Service
public class MembershipRequestsService {
    @Autowired
    private MembershipRequestsRepository membershipRequestsRepository;

    @Autowired
    private ClubMembershipRepository clubMembershipRepository;

    @Autowired
    private ClubRolesRepository clubRolesRepository;

    public List<MembershipRequestsEntity> getAllMembershipRequests() {

        return membershipRequestsRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    public ResponseEntity<MembershipRequestsEntity> getMembershipRequestById(Integer id) {
        MembershipRequestsEntity membershipRequest = membershipRequestsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membership request not found with id: " + id));
        return ResponseEntity.ok().body(membershipRequest);

    }

    public ResponseEntity<MembershipRequestsEntity> createMembershipRequest(
            MembershipRequestsEntity membershipRequestsEntity) {

        MembershipRequestsEntity membershipRequest = membershipRequestsEntity;
        membershipRequest.setStatus("Pending");
        MembershipRequestsEntity createdMembershipRequest = membershipRequestsRepository.save(membershipRequest);
        return ResponseEntity.status(201).body(createdMembershipRequest);

    }

    public ResponseEntity<MembershipRequestsEntity> approveMembershipRequest(Integer id) {

        MembershipRequestsEntity existingMembershipRequest = membershipRequestsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membership request not found with id: " + id));

        existingMembershipRequest.setStatus("Approved");
        ClubMembershipEntity newClubMembership = new ClubMembershipEntity();
        newClubMembership.setClub(existingMembershipRequest.getClub());
        newClubMembership.setUser(existingMembershipRequest.getUser());
        clubMembershipRepository.save(newClubMembership);

        final MembershipRequestsEntity updatedMembershipRequest = membershipRequestsRepository
                .save(existingMembershipRequest);
        return ResponseEntity.ok(updatedMembershipRequest);
    }

    public ResponseEntity<MembershipRequestsEntity> updateMembershipRequest(Integer id,
            MembershipRequestsEntity membershipRequest) {

        MembershipRequestsEntity existingMembershipRequest = membershipRequestsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membership request not found with id: " + id));

        if (membershipRequest.getStatus() != null) {
            existingMembershipRequest.setStatus(membershipRequest.getStatus());
        }

        if ("Approved".equals(membershipRequest.getStatus())) {

            ClubMembershipEntity newClubMembership = new ClubMembershipEntity();
            newClubMembership.setClub(existingMembershipRequest.getClub());
            newClubMembership.setUser(existingMembershipRequest.getUser());

            ClubRolesEntity defaultRole = clubRolesRepository.findById(2)
                    .orElseThrow(() -> new RuntimeException("Default club role not found"));

            newClubMembership.setRole(defaultRole);

            clubMembershipRepository.save(newClubMembership);

        } else if ("Rejected".equals(membershipRequest.getStatus())) {
            return deleteMembershipRequest(existingMembershipRequest.getId());
        } else {
            throw new RuntimeException("Invalid status: " + membershipRequest.getStatus()
                    + ". Only 'Approved' or 'Rejected' are allowed.");
        }

        final MembershipRequestsEntity updatedMembershipRequest = membershipRequestsRepository
                .save(existingMembershipRequest);
        return ResponseEntity.ok(updatedMembershipRequest);
    }

    public ResponseEntity<MembershipRequestsEntity> deleteMembershipRequest(Integer id) {
        MembershipRequestsEntity membershipRequest = membershipRequestsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membership request not found with id: " + id));
        membershipRequestsRepository.delete(membershipRequest);
        return ResponseEntity.ok().build();
    }
}
