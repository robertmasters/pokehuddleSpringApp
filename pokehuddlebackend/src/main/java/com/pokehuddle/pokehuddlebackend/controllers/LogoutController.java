package com.pokehuddle.pokehuddlebackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogoutController {
    @Autowired
    private TokenStore tokenStore;

    @RequestMapping(value = {"/oauth/revoke-token}", "/logout"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void logoutSelf(HttpServletRequest request) {
        //header that will contain the access token token
        String authHeader = request.getHeader("Authorization");
        //if empty means that nothing is done so user is already loged out
        if(authHeader != null) {
            //get rid of word bearer and any leading or trailing spaces, which leaves only the access token
            String tokenValue = authHeader.replace("Bearer", "").trim();
            //take the access token that has been striped of word bearer and find it in token store, then remove it, which means that the access token is no longer good
            OAuth2AccessToken auth2AccessToken = tokenStore.readAccessToken(tokenValue);
            tokenStore.removeAccessToken(auth2AccessToken);
        }
    }
}
