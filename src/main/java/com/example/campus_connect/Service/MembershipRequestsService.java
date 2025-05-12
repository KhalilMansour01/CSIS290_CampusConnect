package com.example.campus_connect.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.campus_connect.Entity.MembershipRequestsEntity;
import com.example.campus_connect.Repository.MembershipRequestsRepository;

@Service
public class MembershipRequestsService {
    @Autowired
    private MembershipRequestsRepository membershipRequestsRepository;

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
