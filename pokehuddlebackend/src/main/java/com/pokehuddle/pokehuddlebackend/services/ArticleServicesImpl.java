package com.pokehuddle.pokehuddlebackend.services;

import com.pokehuddle.pokehuddlebackend.models.Article;
import com.pokehuddle.pokehuddlebackend.repositories.ArticleRepository;
import com.pokehuddle.pokehuddlebackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class ArticleServicesImpl implements ArticleServices{

    @Autowired
    private ArticleRepository articlerepository;

    @Autowired
    private UserRepository userrepository;

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

    @Override
    public void delete(long articleid) {
        if (articlerepository.findById(articleid).isPresent()) {
            articlerepository.deleteById(articleid);
        } else {
            throw new EntityNotFoundException("Article " + articleid + " not found!");
        }
    }

    @Transactional
    @Override
    public Article update(Article updateArticle, long articleid) {

        //handles id
        Article currentArticle = articlerepository.findById(articleid)
                .orElseThrow(() -> new EntityNotFoundException("Article " + articleid + " not found!"));



        //primitive data type/String
        if (updateArticle.getTitle() != null) {
            currentArticle.setTitle(updateArticle.getTitle());
        }

        if (updateArticle.getBody() != null) {
            currentArticle.setBody(updateArticle.getBody());
        }

        if (updateArticle.getAuthor() != null) {
            currentArticle.setAuthor(updateArticle.getAuthor());
        }

        //since data type is an object I can check for null
        if (updateArticle.getUser() != null) {
            currentArticle.setUser(userrepository.findById(updateArticle.getUser()
                    .getUserid())
                    .orElseThrow(() -> new EntityNotFoundException("User " + updateArticle.getUser().getUserid() + " not found!")));
        }

        return articlerepository.save(currentArticle);
    }
}
