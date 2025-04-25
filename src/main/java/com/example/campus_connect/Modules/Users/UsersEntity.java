package com.example.campus_connect.Modules.Users;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UsersEntity {

    @Id
    @Column(nullable = false, updatable = false)
    private String id; // Example: USR250001

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    // @Pattern(
    // regexp = "^[A-Za-z0-9._%+-]+@(std\\.)?balamand\\.edu\\.lb$",
    // message = "Email must be a valid University email"
    // )
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_type", length = 20, nullable = false)
    private String userType; 
    // student/officer/osa_admin
    // user_type::text = ANY (ARRAY[
    //     'Student'::character varying, 
    //     'Officer'::character varying, 
    //     'OSA_Admin'::character varying
    //     ]::text[])

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

    public String toString() {
        return "UsersEntity{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                ", createdAt=" + createdAt +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    @PrePersist
    
    public void generateCustomUserId() {
        if (this.id == null) {
            // You can leave this empty, as the trigger will handle ID generation
        }
    }
}
