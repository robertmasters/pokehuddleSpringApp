package com.pokehuddle.pokehuddlebackend.services;

import com.pokehuddle.pokehuddlebackend.models.User;
import com.pokehuddle.pokehuddlebackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
