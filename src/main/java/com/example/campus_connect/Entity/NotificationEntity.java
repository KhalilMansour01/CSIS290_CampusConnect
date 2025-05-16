package com.example.campus_connect.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "notifications")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The user who receives this notification
    @ManyToOne
    @JoinColumn(name = "recipient_id", nullable = false)
    private UsersEntity recipient;

    // What this notification is related to: EVENT or MEMBERSHIP
    @Column(name = "reference_type", nullable = false, length = 20)
    private String referenceType; // Values: "EVENT", "MEMBERSHIP_REQUEST"

    // ID of the specific Event or MembershipRequest
    @Column(name = "reference_id", nullable = false)
    private Integer referenceId;

    // What kind of action triggered this: PENDING, APPROVED, REJECTED
    @Column(name = "notification_type", nullable = false, length = 20)
    private String notificationType; // Values: "PENDING", "STATUS_UPDATE" (e.g., APPROVED, REJECTED)

    // Notification message to be displayed to the recipient
    @Column(name = "message", nullable = false)
    private String message;

    // Whether the recipient has read this notification
    @Column(name = "is_read", nullable = false)
    private boolean isRead = false;

    @Column(name = "created_at", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsersEntity getRecipient() {
        return recipient;
    }

    public void setRecipient(UsersEntity recipient) {
        this.recipient = recipient;
    }

    public String getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

    public Integer getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Integer referenceId) {
        this.referenceId = referenceId;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
