package com.pokehuddle.pokehuddlebackend.controllers;

import com.pokehuddle.pokehuddlebackend.models.Article;
import com.pokehuddle.pokehuddlebackend.services.ArticleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleServices articleServices;

    // .../articles/articles
    @GetMapping(value = "/articles", produces = "application/json")
    public ResponseEntity<?> listAllArticles() {
        List<Article> returnList = articleServices.findAllArticles();
        return new ResponseEntity<>(returnList, HttpStatus.OK);
    }

    //.../articles/2
    @GetMapping(value = "/article/{articleid}", produces = "application/json")
    public ResponseEntity<?> findArticleById(@PathVariable long articleid) {
        Article returnArticle = articleServices.findArticleById(articleid);
        return new ResponseEntity<>(returnArticle, HttpStatus.OK);
    }

    //.../articles/articletitle
    @GetMapping(value = "/article/articleslike/{subarticletitle}", produces = "application/json")
    public ResponseEntity<?> findArticlesLikeTitle(@PathVariable String subarticletitle) {
        List<Article> returnList = articleServices.findByTitleLike(subarticletitle);
        return new ResponseEntity<>(returnList, HttpStatus.OK);
    }
}
