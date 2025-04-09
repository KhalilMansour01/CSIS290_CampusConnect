package com.example.club_connect.Entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "event_attendance")
public class EventAttendanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private EventsEntity event;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UsersEntity user;

    @Column(name = "attended")
    private Boolean attended = false;

    @Column(name = "check_in_time")
    private LocalDateTime checkInTime;

    // Getters and setters...
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public EventsEntity getEvent() {
        return event;
    }
    public void setEvent(EventsEntity event) {
        this.event = event;
    }
    public UsersEntity getUser() {
        return user;
    }
    public void setUser(UsersEntity user) {
        this.user = user;
    }
    public Boolean getAttended() {
        return attended;
    }
    public void setAttended(Boolean attended) {
        this.attended = attended;
    }
    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }
    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }
}
