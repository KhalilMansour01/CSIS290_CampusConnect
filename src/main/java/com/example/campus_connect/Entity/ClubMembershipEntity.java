package com.example.campus_connect.Entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "club_membership")
public class ClubMembershipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UsersEntity user;

    @ManyToOne
    @JoinColumn(name = "club_id", nullable = false)
    private ClubsEntity club;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private ClubRolesEntity role;

    @Column(name = "joined_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate joinedDate = LocalDate.now();

    // Getters and setters...
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }

    public ClubsEntity getClub() {
        return club;
    }

    public void setClub(ClubsEntity club) {
        this.club = club;
    }

    public ClubRolesEntity getRole() {
        return role;
    }

    public void setRole(ClubRolesEntity role) {
        this.role = role;
    }

    public LocalDate getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(LocalDate joinedDate) {
        this.joinedDate = joinedDate;
    }
}
