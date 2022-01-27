package com.pokehuddle.pokehuddlebackend.services;

import com.pokehuddle.pokehuddlebackend.models.Article;
import com.pokehuddle.pokehuddlebackend.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
