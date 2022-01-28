package com.pokehuddle.pokehuddlebackend.services;

import com.pokehuddle.pokehuddlebackend.models.User;

import java.util.List;

public interface UserServices {
    User save(User user);

    List<User> findAllUsers();
    User findUserById(long id);
    User findUserByUsername(String username);
    List<User> findByUserNamelike(String subname);

}
