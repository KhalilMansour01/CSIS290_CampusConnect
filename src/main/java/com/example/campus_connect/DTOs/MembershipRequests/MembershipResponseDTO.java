package com.example.campus_connect.DTOs.MembershipRequests;

import java.time.LocalDate;

import com.example.campus_connect.DTOs.Clubs.ClubResponseDTO;
import com.example.campus_connect.DTOs.Users.UserResponseDTO;

public class MembershipResponseDTO {
    private Integer id;
    private UserResponseDTO user;
    private ClubResponseDTO club;
    private String status;
    private LocalDate requestDate;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }
}
