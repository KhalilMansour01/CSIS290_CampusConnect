package com.example.campus_connect.Modules.MembershipRequests;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class MembershipRequestsService {
    @Autowired
    private MembershipRequestsRepository membershipRequestsRepository;

    public List<MembershipRequestsEntity> getAllMembershipRequests() {
        List<MembershipRequestsEntity> membershipRequestsList = membershipRequestsRepository.findAll();

        return membershipRequestsList;
    }

    public ResponseEntity<MembershipRequestsEntity> getMembershipRequestById(Integer id) {
        MembershipRequestsEntity membershipRequest = membershipRequestsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membership request not found with id: " + id));
        return ResponseEntity.ok().body(membershipRequest);

    }

    public ResponseEntity<MembershipRequestsEntity> createMembershipRequest(
            MembershipRequestsEntity membershipRequest) {
        if (membershipRequestsRepository.existsById(membershipRequest.getId())) {
            throw new RuntimeException("Membership request already exists with id: " + membershipRequest.getId());
        } else {
            MembershipRequestsEntity createdMembershipRequest = membershipRequestsRepository.save(membershipRequest);
            return ResponseEntity.status(201).body(createdMembershipRequest);
        }
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
