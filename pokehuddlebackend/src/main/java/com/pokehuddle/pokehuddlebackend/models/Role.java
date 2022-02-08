package com.pokehuddle.pokehuddlebackend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roleid;

    @Column(nullable = false)
    private String role; // only 2 roles will be allowed in the gui, admin or member

    //Example of how I would have implemented a many to many instead of doing the userRolesID and Userroles classes
//    @ManyToMany(mappedBy = "roles")
//    @JsonIgnoreProperties(value = "roles", allowSetters = true)
//    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "role",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnoreProperties(value = "role", allowSetters = true)
    private Set<UserRoles> users = new HashSet<>(); //using a set because a set only allows a unique element

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public long getRoleid() {
        return roleid;
    }

    public void setRoleid(long roleid) {
        this.roleid = roleid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<UserRoles> getUsers() {
        return users;
    }

    public void setUsers(Set<UserRoles> users) {
        this.users = users;
    }
}
