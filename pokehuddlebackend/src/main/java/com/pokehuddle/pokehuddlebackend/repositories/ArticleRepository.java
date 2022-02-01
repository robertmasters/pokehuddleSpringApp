package com.pokehuddle.pokehuddlebackend.repositories;

import com.pokehuddle.pokehuddlebackend.models.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//crud repository connects the database to the rest of the application
public interface ArticleRepository extends CrudRepository <Article, Long>{
    Article findByTitle(String title);
    List<Article> findByTitleContainingIgnoringCase(String liketitle);
}
