package com.example.campus_connect.DTOs.ClubMembership;

import java.time.LocalDate;

import com.example.campus_connect.Entity.ClubRolesEntity;
import com.example.campus_connect.DTOs.Clubs.ClubResponseDTO;
import com.example.campus_connect.DTOs.Users.UserResponseDTO;

public class ClubMembershipResponseDTO {

    private Integer id;
    private UserResponseDTO user;
    private ClubResponseDTO club;
    private ClubRolesEntity role;
    private LocalDate joinedDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }

    public ClubResponseDTO getClub() {
        return club;
    }

    public void setClub(ClubResponseDTO club) {
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
