package com.example.club_connect.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UsersEntity {
    
   @Id
    @Column(name = "id", length = 10)
    private String id;  // Example: USR250001

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    // @Pattern(
    //     regexp = "^[A-Za-z0-9._%+-]+@(std\\.)?balamand\\.edu\\.lb$",
    //     message = "Email must be a valid University email"
    // )
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_type", length = 20, nullable = false)
    private String userType; // student/officer/osa_admin

    @Column(name = "created_at", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    // --- Getters and Setters ---

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
