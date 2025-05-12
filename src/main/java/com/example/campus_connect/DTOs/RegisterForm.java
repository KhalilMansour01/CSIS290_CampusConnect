package com.example.campus_connect.DTOs;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RegisterForm {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email(message = "Invalid email format")
    @ValidEmailDomain(allowedDomains = { "balamand.edu.lb",
            "std.balamand.edu.lb" }, message = "Email must be a university email")
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String userType = "Student"; // Default to Student

    @NotNull
    private LocalDate dateOfBirth;

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}