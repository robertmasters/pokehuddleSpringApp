package com.pokehuddle.pokehuddlebackend.services;

import com.pokehuddle.pokehuddlebackend.models.Role;
import com.pokehuddle.pokehuddlebackend.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class RoleServicesImpl implements RoleServices{

    @Autowired
    private RoleRepository rolerepository;

    @Override
    public Role save(Role role) {
        return rolerepository.save(role);
    }
}
