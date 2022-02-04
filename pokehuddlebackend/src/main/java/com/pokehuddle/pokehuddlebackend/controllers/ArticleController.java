package com.pokehuddle.pokehuddlebackend.controllers;

import com.pokehuddle.pokehuddlebackend.models.Article;
import com.pokehuddle.pokehuddlebackend.services.ArticleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    //POST articles/article
    //Request Body -> contain new article data
    @PostMapping(value = "/article", consumes = "application/json", produces = "application/json")
    private ResponseEntity<?> addNewArticle(@Valid @RequestBody Article newArticle) {
        newArticle.setArticleid(0);
        newArticle = articleServices.save(newArticle);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newArticleURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{articleid}")
                .buildAndExpand(newArticle.getArticleid()).
                toUri();
        responseHeaders.setLocation(newArticleURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    //PUT article/id
    @PutMapping(value = "/article/{articleid}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> updateFullArticle(@Valid @RequestBody Article updateArticle, @PathVariable long articleid) {

        updateArticle.setArticleid(articleid);
        articleServices.save(updateArticle);
        return new ResponseEntity<>(updateArticle, HttpStatus.OK);
    }

    //PATCH article/id
    @PatchMapping(value = "article/{articleid}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> updateArticle(@RequestBody Article updateArticle, @PathVariable long articleid) {
        updateArticle = articleServices.update(updateArticle, articleid);
        return new ResponseEntity<>(updateArticle, HttpStatus.OK);
    }

    //DELETE user/id
    @DeleteMapping(value = "/article/{articleid}")
    public ResponseEntity<?> deleteArticleById(@PathVariable long articleid) {
        articleServices.delete(articleid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
