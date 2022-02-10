package com.pokehuddle.pokehuddlebackend.services;

import com.pokehuddle.pokehuddlebackend.exceptions.ResourceNotFoundException;
import com.pokehuddle.pokehuddlebackend.models.User;
import com.pokehuddle.pokehuddlebackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "securityUserService")
public class SecurityUserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws ResourceNotFoundException {
        User user = userRepository.findByUsername(s);
        if (user == null) {
            throw new ResourceNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthority());
    }
}
