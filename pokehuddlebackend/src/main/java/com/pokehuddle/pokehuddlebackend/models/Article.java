package com.pokehuddle.pokehuddlebackend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "articles")
public class Article extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long articleid;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    @Column(nullable = false)
    private String author;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnoreProperties(value = "articles", allowSetters = true)
    private User user;

    public Article() {
    }

    public Article(String title, String body, String author, User user) {
        this.title = title;
        this.body = body;
        this.author = author;
        this.user = user;
    }

    public long getArticleid() {
        return articleid;
    }

    public void setArticleid(long articleid) {
        this.articleid = articleid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
