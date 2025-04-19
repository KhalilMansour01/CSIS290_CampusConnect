package com.example.club_connect.Modules.ClubRoles;

import jakarta.persistence.*;

@Entity
@Table(name = "club_roles")
public class ClubRolesEntity {

    // to reset id counter
    // SELECT setval('club_roles_id_seq', (SELECT MAX(id) FROM club_roles));
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_name", length = 50, nullable = false)
    private String roleName;

    // Getters and setters...
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}