package com.pokehuddle.pokehuddlebackend.repositories;

import com.pokehuddle.pokehuddlebackend.models.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//crud repository connects the database to the rest of the application
public interface RoleRepository extends CrudRepository<Role, Long> {

    @Modifying
    @Query(value = "UPDATE roles SET name = :name, lastmodifiedby = :uname, lastmodifieddate = CURRENT_TIMESTAMP WHERE roleid = :roleid", nativeQuery = true)
    void updateRoleName(String name, long roleid, String uname);

}
