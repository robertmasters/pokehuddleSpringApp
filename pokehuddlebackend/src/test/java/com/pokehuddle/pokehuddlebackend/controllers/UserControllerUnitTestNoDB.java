package com.pokehuddle.pokehuddlebackend.controllers;

import com.pokehuddle.pokehuddlebackend.PokehuddleBackendApplicationTests;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PokehuddleBackendApplicationTests.class,
properties = {"command.line.runner.enabled = false"})
public class UserControllerUnitTestNoDB {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void listAllUsers() {
    }

    @Test
    public void findUserById() {
    }

    @Test
    public void findUserByUsername() {
    }

    @Test
    public void findUsersLikeUsername() {
    }

    @Test
    public void addUser() {
    }

    @Test
    public void updateFullUser() {
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void deleteUserById() {
    }
}