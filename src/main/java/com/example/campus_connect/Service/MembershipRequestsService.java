package com.example.campus_connect.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.campus_connect.Entity.ClubMembershipEntity;
import com.example.campus_connect.Entity.ClubRolesEntity;
import com.example.campus_connect.Entity.MembershipRequestsEntity;
import com.example.campus_connect.Entity.NotificationEntity;
import com.example.campus_connect.Entity.UsersEntity;
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

    @Autowired
    private NotificationService notificationService;

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

        membershipRequestsEntity.setStatus("Pending");
        MembershipRequestsEntity createdMembershipRequest = membershipRequestsRepository.save(membershipRequestsEntity);

        // Notify the club officer (president)
        UsersEntity officer = createdMembershipRequest.getClub().getPresident();

        NotificationEntity notification = new NotificationEntity();
        notification.setRecipient(officer); // club president
        notification.setReferenceId(createdMembershipRequest.getId());
        notification.setReferenceType("MEMBERSHIP_REQUEST");
        notification.setNotificationType("PENDING");
        notification.setMessage("A new membership request is pending your approval.");

        notificationService.sendNotification(notification);

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

        UsersEntity student = existingMembershipRequest.getUser();
        String status = membershipRequest.getStatus();

        if ("Approved".equals(status)) {

            ClubMembershipEntity newClubMembership = new ClubMembershipEntity();
            newClubMembership.setClub(existingMembershipRequest.getClub());
            newClubMembership.setUser(existingMembershipRequest.getUser());

            ClubRolesEntity defaultRole = clubRolesRepository.findById(2)
                    .orElseThrow(() -> new RuntimeException("Default club role not found"));

            newClubMembership.setRole(defaultRole);

            clubMembershipRepository.save(newClubMembership);

            // Notify the student of approval
            NotificationEntity notification = new NotificationEntity();
            notification.setRecipient(student);
            notification.setReferenceId(existingMembershipRequest.getId());
            notification.setReferenceType("MEMBERSHIP_REQUEST");
            notification.setNotificationType("STATUS_UPDATE");
            notification.setMessage("Your membership request has been approved.");
            notificationService.sendNotification(notification);

        } else if ("Rejected".equals(status)) {

            // Notify the student of rejection
            NotificationEntity notification = new NotificationEntity();
            notification.setRecipient(student);
            notification.setReferenceId(existingMembershipRequest.getId());
            notification.setReferenceType("MEMBERSHIP_REQUEST");
            notification.setNotificationType("STATUS_UPDATE");
            notification.setMessage("Your membership request has been rejected.");
            notificationService.sendNotification(notification);

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
