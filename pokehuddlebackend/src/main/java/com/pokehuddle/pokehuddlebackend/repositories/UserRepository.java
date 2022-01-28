package com.pokehuddle.pokehuddlebackend.repositories;

import com.pokehuddle.pokehuddlebackend.models.User;
import org.springframework.data.repository.CrudRepository;
//crud repository connects the database to the rest of the application
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
