package com.pokehuddle.pokehuddlebackend.services;

import com.pokehuddle.pokehuddlebackend.models.User;
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

    //whenever a method changes something in the database make it transactional
    @Transactional
    @Override
    public User save(User user) {
        return userrepository.save(user);
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
    public List<User> findByUserNamelike(String subname) {
        return null;
    }
}
