package com.pokehuddle.pokehuddlebackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private String CLIENT_ID = System.getenv("OAUTHCLIENTID");
    private String CLIENT_SECRET = System.getenv("OAUTHCLIENTSECRET");

    //Hardcoded environment variables
//    private String CLIENT_ID = "lambda-client";
//    private String CLIENT_SECRET = "lambda-secret";

    private final String GRANT_TYPE_PASSWORD = "password";
    private final String AUTHORIZATION_CODE = "authorization_code";
    private final String SCOPE_READ = "read";
    private final String SCOPE_WRITE = "write";
    private final String SCOPE_TRUST = "trust";

    //-1 means that the access token will never expire
    private final int ACCESS_TOKEN_VALIDITY_SECONDS = -1;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        //reason to store in memory
        //memory is fast to access
        //if system is shut down everyone looses access and have to start the auth process over
        //memory is alot harder to hack than disc space
        configurer.inMemory()
                .withClient(CLIENT_ID)
                .secret(encoder.encode(CLIENT_SECRET))
                .authorizedGrantTypes(SCOPE_READ, SCOPE_WRITE, SCOPE_TRUST)
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
       endpoints.tokenStore(tokenStore)
               .authenticationManager(authenticationManager);
       endpoints.pathMapping("/oauth/token", "/login");
    }
}
