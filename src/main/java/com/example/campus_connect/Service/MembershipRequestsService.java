package com.example.campus_connect.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.campus_connect.DTOs.MembershipRequests.MembershipRequestDTO;
import com.example.campus_connect.Entity.MembershipRequestsEntity;
import com.example.campus_connect.DTOs.MembershipRequests.MembershipResponseDTO;
import com.example.campus_connect.Mapper.MembershipRequestsMapper;
import com.example.campus_connect.Repository.MembershipRequestsRepository;

@Service
public class MembershipRequestsService {
    @Autowired
    private MembershipRequestsRepository membershipRequestsRepository;

    @Autowired
    private MembershipRequestsMapper membershipRequestsMapper;

    public List<MembershipResponseDTO> getAllMembershipRequests() {

        return membershipRequestsRepository.findAll().stream()
                .map(membershipRequestsMapper::toResponseDTO)
                .toList();
    }

    public ResponseEntity<MembershipResponseDTO> getMembershipRequestById(Integer id) {
        MembershipRequestsEntity membershipRequest = membershipRequestsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membership request not found with id: " + id));
        return ResponseEntity.ok().body(membershipRequestsMapper.toResponseDTO(membershipRequest));

    }

    public ResponseEntity<MembershipResponseDTO> createMembershipRequest(
            MembershipRequestDTO membershipRequestDTO) {

        MembershipRequestsEntity membershipRequest = membershipRequestsMapper.toEntity(membershipRequestDTO);
        membershipRequest.setStatus("PENDING");
        
        MembershipRequestsEntity createdMembershipRequest = membershipRequestsRepository.save(membershipRequest);
        MembershipResponseDTO responseDto = membershipRequestsMapper.toResponseDTO(createdMembershipRequest);
        return ResponseEntity.status(201).body(responseDto);
        
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
