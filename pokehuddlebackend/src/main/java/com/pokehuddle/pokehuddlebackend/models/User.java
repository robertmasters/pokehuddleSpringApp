package com.pokehuddle.pokehuddlebackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends Auditable{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    @Column(unique = true, nullable = false)
    private String username;

    private String email;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    //cascade type all makes it so that whatever happens to user also happens to article
    //orphan removal makes it so that if there is an article that isnt related to any users(for whatever reason), then we want to get rid of that article, orphan removal will delete that article
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "user", allowSetters = true)
    private List<Article> articles = new ArrayList<>();

    //    //Example of how I would have implemented a many to many instead of doing the userRolesID and Userroles classes
//    @ManyToMany()
//    @JoinTable(name = "userroles",
//            joinColumns = @JoinColumn(name = "userid"),
//            inverseJoinColumns = @JoinColumn(name = "roleid"))
//    @JsonIgnoreProperties(value = "users")
//    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties(value = "user", allowSetters = true)
    private Set<UserRoles> roles = new HashSet<>();

    public User() {
    }

    //if it is a single item, add it to the constructor, if its a collection dont add it to the constructor.
    public User(String username, String email, String password) {
        setUsername(username);
        this.email = email;
        setPassword(password);
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    //set the password after we know it's been already encrypted somewhere else, so that we dont reencrypt it again
    public void setNoEncryptPassword(String password) {
        this.password = password;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public Set<UserRoles> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRoles> roles) {
        this.roles = roles;
    }

    @JsonIgnore
    public List<SimpleGrantedAuthority> getAuthority() {
        List<SimpleGrantedAuthority> returnList = new ArrayList<>();

        for(UserRoles r : this.roles) {
            String myRole = "ROLE_" + r.getRole().getName().toUpperCase();
            returnList.add(new SimpleGrantedAuthority(myRole));
        }

        return returnList;
    }
}
