package com.pokehuddle.pokehuddlebackend.services;

import com.pokehuddle.pokehuddlebackend.models.Article;
import com.pokehuddle.pokehuddlebackend.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class ArticleServicesImpl implements ArticleServices{

    @Autowired
    private ArticleRepository articlerepository;

    @Transactional
    @Override
    public Article save(Article article) {
        return articlerepository.save(article);
    }

    @Override
    public List<Article> findAllArticles() {
        List<Article> list = new ArrayList<>();
        articlerepository.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Article findArticleById(long id) {
        return articlerepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Article " + id + " Not Found"));
    }

    @Override
    public List<Article> findByTitleLike(String subtitle) {
        List<Article> returnList = articlerepository.findByTitleContainingIgnoringCase(subtitle);
        return returnList;
    }


}
