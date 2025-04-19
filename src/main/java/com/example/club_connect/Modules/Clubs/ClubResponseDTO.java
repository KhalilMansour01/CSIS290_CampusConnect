package com.example.club_connect.Modules.Clubs;

import com.example.club_connect.Modules.Users.UserResponseDTO;

// Response DTO for sending club data to the client
// This DTO is used to transfer data from the server to the client
public class ClubResponseDTO {
    private Integer id;
    private String name;
    private String description;
    private String category;
    private String status;
    private UserResponseDTO president;

    // Getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserResponseDTO getPresident() {
        return president;
    }

    public void setPresident(UserResponseDTO president) {
        this.president = president;
    }
}