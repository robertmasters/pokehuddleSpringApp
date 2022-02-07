package com.pokehuddle.pokehuddlebackend.models;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

//this never gets saved on the database, its used instead to represent the primary key combination thats getting saved in user roles
@Embeddable
public class UserRolesId implements Serializable { //serializable helps jackson in going from json to java and back
     private long user;
     private long role;

    public UserRolesId() {
    }

    public UserRolesId(long user, long role) {
        this.user = user;
        this.role = role;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public long getRole() {
        return role;
    }

    public void setRole(long role) {
        this.role = role;
    }

    //equals and hashcode are used by java to determine if 2 objects are the same
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        UserRolesId that = (UserRolesId) o;
        return this.user == that.user &&
                role == that.role;
    }

    @Override
    public int hashCode() {
        return 37;
    }
}
