package com.example.campus_connect.Modules.Clubs;

// Request DTO for creating or updating a club
// This DTO is used to transfer data from the client to the server
public class ClubRequestDTO {
    private String name;
    private String description;
    private String category;
    private String status;
    private String presidentId;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getStatus() {
        return status;
    }

    public String getPresidentId() {
        return presidentId;
    }
}
