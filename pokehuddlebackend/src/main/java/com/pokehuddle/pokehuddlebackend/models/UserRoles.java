package com.pokehuddle.pokehuddlebackend.models;

import javax.persistence.*;

@Entity
@Table(name = " userroles")
public class UserRole {
    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "roleid")
    private Role role;

    public UserRole() {
    }

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
