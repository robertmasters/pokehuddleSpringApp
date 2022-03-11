package com.pokehuddle.pokehuddlebackend.controllers;

import com.pokehuddle.pokehuddlebackend.models.User;
import com.pokehuddle.pokehuddlebackend.models.UserMinimum;
import com.pokehuddle.pokehuddlebackend.models.UserRoles;
import com.pokehuddle.pokehuddlebackend.services.RoleServices;
import com.pokehuddle.pokehuddlebackend.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class OpenController {

    @Autowired
    UserServices userServices;

    @Autowired
    RoleServices roleServices;

    //http:/localhost:2019/createnewuser
    //body -> username, password, email

    @PostMapping(value = "/createnewuser", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addSelf(HttpServletRequest httpServletRequest, @RequestBody UserMinimum newminuser)  throws Exception {
        //create new user
        User newuser = new User();
        //set up needed data
        newuser.setUsername(newminuser.getUsername());
        newuser.setPassword(newminuser.getPassword());
        newuser.setEmail(newminuser.getEmail());
        //set up roles that is needed
        Set<UserRoles> newRoles = new HashSet<>();
        newRoles.add(new UserRoles(newuser, roleServices.findByName("USER")));

        //save it
        newuser = userServices.save(newuser);

        HttpHeaders responseHeaders = new HttpHeaders();
        //http://localhost:2019/users/user/id
        URI newUserURI = ServletUriComponentsBuilder.fromUriString(httpServletRequest.getServerName() + ":" + httpServletRequest.getLocalPort() + "/users/user/{userid}")
                        .buildAndExpand(newuser.getUserid()).toUri();
        responseHeaders.setLocation((newUserURI));

        return new ResponseEntity<>(
                responseHeaders,
                HttpStatus.CREATED);
    }

}

