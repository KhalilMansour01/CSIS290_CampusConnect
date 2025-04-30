package com.example.campus_connect.DTOs.Users;

public class UserRequestDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String userType;
    private String dateOfBirth;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
}
