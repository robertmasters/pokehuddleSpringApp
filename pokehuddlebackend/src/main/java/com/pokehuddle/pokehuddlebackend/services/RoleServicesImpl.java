package com.pokehuddle.pokehuddlebackend.services;

import com.pokehuddle.pokehuddlebackend.models.Role;
import com.pokehuddle.pokehuddlebackend.repositories.RoleRepository;
import com.pokehuddle.pokehuddlebackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;


@Transactional
@Service
public class RoleServicesImpl implements RoleServices{

    @Autowired
    private RoleRepository rolerepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAuditing userAuditing;

    @Override
    public Role save(Role role) {
        return rolerepository.save(role);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void deleteAll() {
        rolerepository.deleteAll();
    }

    @Override
    public List<Role> findAll() {

        List<Role> list = new ArrayList<>();

        rolerepository.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Role findRoleById(long id) {

        return rolerepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role id " + id + " not found!"));
    }

    @Override
    public Role findByName(String name) {
        Role rr = rolerepository.findByNameIgnoreCase(name);

        if (rr != null) {
            return rr;
        } else {
            throw new EntityNotFoundException(name);
        }
    }

    @Override
    public Role update(long id, Role role) {
        return null;
    }

    //trick on how to get a username
    @Transactional
    public void updateRoleName(String name, long roleid) {
        rolerepository.updateRoleName(name, roleid, userAuditing.getCurrentAuditor().get());
    }
}
