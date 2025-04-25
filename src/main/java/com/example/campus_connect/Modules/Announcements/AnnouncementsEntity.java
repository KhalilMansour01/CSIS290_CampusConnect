package com.example.campus_connect.Modules.Announcements;

import java.time.LocalDate;

import com.example.campus_connect.Modules.Clubs.ClubsEntity;

import jakarta.persistence.*;

@Entity
@Table(name = "announcements")
public class AnnouncementsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "club_id", nullable = false)
    private ClubsEntity club;

    @Column(name = "message", columnDefinition = "TEXT", nullable = false)
    private String message;

    @Column(name = "priority", length = 20)
    private String priority;
    // Normal, Important, Urgent
    // priority::text = ANY (ARRAY[
    // 'Normal'::character varying,
    // 'Important'::character varying,
    // 'Urgent'::character varying
    // ]::text[])

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    // Getters and setters...
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClubsEntity getClub() {
        return club;
    }

    public void setClub(ClubsEntity club) {
        this.club = club;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}
