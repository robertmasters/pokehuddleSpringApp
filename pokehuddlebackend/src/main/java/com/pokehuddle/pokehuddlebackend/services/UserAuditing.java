package com.pokehuddle.pokehuddlebackend.services;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserAuditing implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        String uname;
        //checks who the authenticated user is
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        //if top code is not null this means we have an authenticated user
        if(authentication != null) {
            uname = authentication.getName();
        } else
        {
            //this default only works if using seed data
            uname = "SYSTEM";
        }
        return Optional.of(uname);
    }
}
