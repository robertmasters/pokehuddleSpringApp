package com.pokehuddle.pokehuddlebackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private String RESOURCE_ID = "resource_id";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID)
                .stateless(false); //this being false helps with unit testing and integration testing otherwise this stateless(false) can be left out, if left to true then you will have to authenticate everytime when you are running unit and integration test

    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(
                        "/","/login",
                        "/h2-console/**",
                        "/swagger-resources/**",
                        "/swagger-resource/**",
                        "/swagger-ui.html",
                        "/v2/api-docs",
                        "/webjars/**",
                        "/createnewuser") // **gives access to /h2-console and anything that gets added to the end
                .permitAll()
                .antMatchers("/logout")
                .authenticated() //anyone who has gotten an access token, has access to antMatchers("/logout")
                .antMatchers(HttpMethod.POST, "/users/**")
                .hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/users/**")
                .hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/users/**")
                .hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/users/**")
                .hasAnyRole("ADMIN")
                .antMatchers("/users/**") //allowing access with no restriction, must be done at the end of all the restricted access otherwise spring security will just skip those
                .authenticated()
                .anyRequest().denyAll() //no one has access to anything unless we give access
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new OAuth2AccessDeniedHandler());
        http.csrf().disable();
        http.headers().frameOptions().disable(); //required for h2 console
        http.logout().disable();
    }
}
