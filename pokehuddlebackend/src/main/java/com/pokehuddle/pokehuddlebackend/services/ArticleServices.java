package com.pokehuddle.pokehuddlebackend.services;

import com.pokehuddle.pokehuddlebackend.models.Article;

import java.util.List;

public interface ArticleServices {
    Article save(Article article);

    List<Article> findAllArticles();
    Article findArticleById(long id);
    List<Article> findByTitleLike(String subtitle);

    void delete(long articleid);

    Article update(Article updateArticle, long articleid);

    public void deleteAll();
}
