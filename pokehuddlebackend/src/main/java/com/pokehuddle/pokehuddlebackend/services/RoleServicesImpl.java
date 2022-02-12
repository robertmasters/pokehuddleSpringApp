package com.pokehuddle.pokehuddlebackend.services;

import com.pokehuddle.pokehuddlebackend.models.Role;
import com.pokehuddle.pokehuddlebackend.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class RoleServicesImpl implements RoleServices{

    @Autowired
    private RoleRepository rolerepository;

    @Autowired
    private UserAuditing userAuditing;

    @Override
    public Role save(Role role) {
        return rolerepository.save(role);
    }

    //trick on how to get a username
    @Transactional
    public void updateRoleName(String name, long roleid) {
        rolerepository.updateRoleName(name, roleid, userAuditing.getCurrentAuditor().get());
    }
}
