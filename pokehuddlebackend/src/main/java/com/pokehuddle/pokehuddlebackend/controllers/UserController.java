package com.pokehuddle.pokehuddlebackend.controllers;

import com.pokehuddle.pokehuddlebackend.models.User;
import com.pokehuddle.pokehuddlebackend.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServices userServices;

    //admin
    // .../users/users
    @GetMapping(value = "/users", produces = "application/json")
    public ResponseEntity<?> listAllUsers() {
        List<User> returnList = userServices.findAllUsers();
        return new ResponseEntity<>(returnList, HttpStatus.OK);
    }
    // .../users/2
    // .../users/username
    // .../users/email

    // .../users/partofusername



}
