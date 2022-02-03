package com.pokehuddle.pokehuddlebackend.controllers;

import com.pokehuddle.pokehuddlebackend.models.User;
import com.pokehuddle.pokehuddlebackend.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
    @GetMapping(value = "/user/{userid}", produces = "application/json")
    public ResponseEntity<?> findUserById(@PathVariable long userid) {
        User returnUser = userServices.findUserById(userid);
        return new ResponseEntity<>(returnUser, HttpStatus.OK);
    }

    // .../users/username
    @GetMapping(value = "/user/username/{username}", produces = "application/json")
    public ResponseEntity<?> findUserByUsername(@PathVariable String username) {
        User returnUser = userServices.findUserByUsername(username);
        return new ResponseEntity<>(returnUser, HttpStatus.OK);
    }
    // .../users/email

    // .../users/partofusername
    @GetMapping(value = "/user/usernamelike/{subusername}", produces = "application/json")
    public ResponseEntity<?> findUsersLikeUsername(@PathVariable String subusername) {
        List<User> returnList = userServices.findByUsernameLike(subusername);
        return new ResponseEntity<>(returnList, HttpStatus.OK);
    }

    //POST users/user
    //Request Body -> contain new user data
    public  ResponseEntity<?> addUser(@Valid @RequestBody User newUser, @PathVariable long userid) { //@valid checks to make sure that everything that should be in the object being sent in is there.
        newUser.setUserid(userid);
        newUser = userServices.save(newUser);

        //http headers - location: link to the newly created restaurant
        HttpHeaders responseHeaders = new HttpHeaders();

        //builds this path../users/user/15 so that a new userid is made
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newUser.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(newUser, responseHeaders, HttpStatus.CREATED);
    }

    //PUT user/id
    @PutMapping(value = "/user/userid", produces = "application/json", consumes = "application/json")
    public  ResponseEntity<?> updateFullUser(@Valid @RequestBody User updateUser) { //@valid checks to make sure that everything that should be in the object being sent in is there.
        updateUser.setUserid(0); //0 is the same as saying you have no id, 0 is a null id
        updateUser = userServices.save(updateUser);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    //PATCH user/id
    @PatchMapping(value = "/user/{userid}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> updateUser(@RequestBody User updateUser, @PathVariable long userid) {
        updateUser = userServices.update(updateUser, userid);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    //DELETE user/id
    @DeleteMapping(value = "/user/{userid}")
    public ResponseEntity<?> deleteUserById(@PathVariable long userid) {
        userServices.delete(userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
