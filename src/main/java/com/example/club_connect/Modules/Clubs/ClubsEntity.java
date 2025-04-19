package com.example.club_connect.Modules.Clubs;

import com.example.club_connect.Modules.Users.UsersEntity;

import jakarta.persistence.*;

@Entity
@Table(name = "clubs")
public class ClubsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "category", length = 50)
    private String category;

    @Column(name = "status", length = 20)
    private String status; // Active or Inactive

    @ManyToOne
    @JoinColumn(name = "president_id")
    private UsersEntity president;

    // Getters and setters...
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

    public UsersEntity getPresident() {
        return president;
    }

    public void setPresident(UsersEntity president) {
        this.president = president;
    }
}
