package com.pokehuddle.pokehuddlebackend.repositories;


import com.pokehuddle.pokehuddlebackend.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//crud repository connects the database to the rest of the application
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    List<User> findByUsernameContainingIgnoringCase(String likeusername);
}
