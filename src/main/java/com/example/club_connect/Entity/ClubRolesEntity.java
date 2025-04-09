package com.example.club_connect.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "club_roles")
public class ClubRolesEntity {

    // nextval('club_roles_id_seq'::regclass)
    @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "club_roles_id_seq")
    // @SequenceGenerator(name = "club_roles_id_seq", sequenceName = "club_roles_id_seq", allocationSize = 1)
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