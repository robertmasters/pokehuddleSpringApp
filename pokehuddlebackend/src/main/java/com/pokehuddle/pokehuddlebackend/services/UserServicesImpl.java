package com.pokehuddle.pokehuddlebackend.services;

import com.pokehuddle.pokehuddlebackend.models.Article;
import com.pokehuddle.pokehuddlebackend.models.Role;
import com.pokehuddle.pokehuddlebackend.models.User;
import com.pokehuddle.pokehuddlebackend.repositories.RoleRepository;
import com.pokehuddle.pokehuddlebackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

//if you have a method in the class that is transactional, you should also make the entire class transactional
@Transactional
@Service(value = "userService")
public class UserServicesImpl implements UserServices{

    @Autowired
    private UserRepository userrepository;

    @Autowired
    private RoleRepository rolerepository;

    //whenever a method changes something in the database make it transactional
    @Transactional
    @Override
    public User save(User user) {
        User newUser = new User();

        if (user.getUserid() != 0) {
            userrepository.findById(user.getUserid())
                    .orElseThrow(() -> new EntityNotFoundException("User " + user.getUserid() + " not found!"));
            newUser.setUserid((user.getUserid()));
        }

        //primitive data type/String
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());

        //collections
        //many to many
        newUser.getRoles().clear();
        for(Role r: user.getRoles()) {
            Role newRole = rolerepository.findById(r.getRoleid())
                    .orElseThrow(() -> new EntityNotFoundException("Role " + r.getRoleid() + " not found"));

            newUser.getRoles().add(newRole);
        }

        //one to many
        newUser.getArticles().clear();
        for (Article a: user.getArticles()) {
            Article newArticle = new Article();
            newArticle.setAuthor(a.getAuthor());
            newArticle.setTitle(a.getTitle());
            newArticle.setBody(a.getBody());
            newArticle.setUser(newUser);

            newUser.getArticles().add(newArticle);
        }

        return userrepository.save(newUser);
    }

    @Override
    public List<User> findAllUsers() {
        List<User> list = new ArrayList<>();
        userrepository.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public User findUserById(long id) {

        return userrepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User " + id + "Not Found!"));
    }

    @Override
    public User findUserByUsername(String username) {
        User returnUser = userrepository.findByUsername(username);
        if(returnUser == null) {
            throw new EntityNotFoundException("User "+ username + " Not Found");
        }
        return returnUser;
    }

    @Override
    public List<User> findByUsernameLike(String subusername) {
        List<User> returnList = userrepository.findByUsernameContainingIgnoringCase(subusername);
        return returnList;
    }

    @Transactional
    @Override
    public void delete(long userid) {
        if (userrepository.findById(userid).isPresent()) {
            userrepository.deleteById(userid);
        } else {
            throw new EntityNotFoundException("User " + userid + " not found!");
        }
    }
}
