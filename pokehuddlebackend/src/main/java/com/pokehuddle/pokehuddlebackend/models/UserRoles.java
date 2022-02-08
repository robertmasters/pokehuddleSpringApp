package com.pokehuddle.pokehuddlebackend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = " userroles")
@IdClass(UserRolesId.class)
public class UserRoles implements Serializable { //using serializable since I am using a compound id in role and user
    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = "roles", allowSetters = true)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "roleid")
    @JsonIgnoreProperties(value = "users", allowSetters = true)
    private Role role;

    public UserRoles() {
    }

    public UserRoles(User user, Role role) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof  UserRoles)) return false;
        UserRoles that = (UserRoles) o;

        //if the compound ids are equal then the objects are equal as far as this application is concerned
        return  ((this.user == null) ? 0 : this.user.getUserid()) == ((that.user == null) ? 0 : that.user.getUserid()) &&
                ((this.role == null) ? 0 : this.role.getRoleid()) == ((that.role == null) ? 0 : that.role.getRoleid());
    }

    @Override
    public int hashCode() {
        return 37;
    }
}
