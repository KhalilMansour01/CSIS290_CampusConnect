package com.example.campus_connect.Modules.Events;

import java.time.LocalDate;

import com.example.campus_connect.Modules.Clubs.ClubsEntity;

import jakarta.persistence.*;

@Entity
@Table(name = "events")
public class EventsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "club_id", nullable = false)
    private ClubsEntity club;

    @Column(name = "event_name", length = 100, nullable = false)
    private String eventName;

    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "location",length = 100)
    private String location;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(length = 20)
    private String status; // Pending, Approved, Rejected

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

    public void setClubId(ClubsEntity club) {
        this.club = club;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
