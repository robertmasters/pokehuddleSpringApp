package com.pokehuddle.pokehuddlebackend.models;

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

    @ManyToMany()
    private Set<User> users = new HashSet<>();

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
}
