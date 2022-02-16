package com.pokehuddle.pokehuddlebackend.services;

import com.pokehuddle.pokehuddlebackend.PokehuddleBackendApplicationTests;
import com.pokehuddle.pokehuddlebackend.exceptions.ResourceNotFoundException;
import com.pokehuddle.pokehuddlebackend.models.Article;
import com.pokehuddle.pokehuddlebackend.models.Role;
import com.pokehuddle.pokehuddlebackend.models.User;
import com.pokehuddle.pokehuddlebackend.models.UserRoles;
import com.pokehuddle.pokehuddlebackend.repositories.RoleRepository;
import com.pokehuddle.pokehuddlebackend.repositories.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PokehuddleBackendApplicationTests.class,
        properties = {"command.line.runner.enabled = false"})
//this method forces the test to run in alphabetical order based off method name, If i want to manipulate the order then I can add a letter to the beginning of the method name such as a_findID, b_findAll, ba_findname, etc, otherwise java will find the most efficient way to run the test
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServicesImplUnitTestNoDB {

    //stubs -> fake methods
    //mock -> fake data
    @Autowired
    private UserServices userServices;

//    @Autowired
//    private RoleServices roleServices;

    @MockBean
    private UserRepository userRepository;

//    @MockBean
//    private RoleRepository roleRepository;

    private List<User> userList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
//        Role r1 = new Role("admin");
//        Role r2 = new Role("user");
//
//        r1.setRoleid(1);
//        r2.setRoleid(2);
//
//        r1 = roleServices.save(r1);
//        r2 = roleServices.save(r2);

        User u1 = new User(
                "admin",
                "admin@pikapika.com",
                "password");
        u1.setUserid(10);
        userList.add(u1);

        User u2 = new User(
                "neo",
                "neo@pikipika.com",
                "pika123");
        u2.setUserid(20);

        u2.getArticles()
                .add(new Article("Pikachu is too popular",
                        "Sic de isto et tutius perducit ad actum ipsum, ut si dico Ego autem vadam lavari, ut mens mea in statu naturae conformior. Et similiter circa alias",
                        "Neo",
                        u2));
        u2.getArticles().get(0).setArticleid(21);
        u2.getArticles()
                .add(new Article("Top Ash Mistakes",
                        "Sic de isto et tutius perducit ad actum ipsum, ut si dico Ego autem vadam",
                        "Neo",
                        u2));
        u2.getArticles().get(0).setArticleid(22);
        userList.add(u2);

        User u3 = new User(
                "scarlett",
                "scar@pikapika.com",
                "pika123"
        );
        u3.setUserid(30);
        u3.getArticles()
                .add(new Article("Mew vs Mewtwo",
                        "Sic de isto et tutius perducit ad actum ipsum, ut si dico “Ego autem vadam",
                        "Scarlett",
                        u2));
        u3.getArticles().get(0).setArticleid(31);
        userList.add(u3);

        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void save() {
        //create a user to save
        User u2 = new User(
                "neo",
                "neo@pikipika.com",
                "pika123");
        u2.getArticles()
                .add(new Article("Pikachu is too popular",
                        "Sic de isto et tutius perducit ad actum ipsum, ut si dico Ego autem vadam lavari, ut mens mea in statu naturae conformior. Et similiter circa alias",
                        "Neo",
                        u2));
        u2.getArticles()
                .add(new Article("Top Ash Mistakes",
                        "Sic de isto et tutius perducit ad actum ipsum, ut si dico Ego autem vadam",
                        "Neo",
                        u2));
        Mockito.when(userRepository.save(any(User.class)))
                .thenReturn(u2);


    }

    @Test
    public void findAllUsers() {
        Mockito.when(userRepository.findAll())
                .thenReturn(userList);
        assertEquals(3, userServices.findAllUsers().size());

    }

    @Test
    public void findUserById() {
        Mockito.when(userRepository.findById(10L))
                .thenReturn(Optional.of(userList.get(0)));
        assertEquals("admin", userServices.findUserById(10).getUsername());
    }

    //if testing an exception use this annotation config
    //if testing an exception use this annotation config
    @Test(expected = ResourceNotFoundException.class)
    public void notFindUserById() {
        Mockito.when(userRepository.findById(100L))
                .thenThrow(ResourceNotFoundException.class);
        //if assertion ever runs that means that there is something wrong!
        assertEquals("admin", userServices.findUserById(10).getUsername());
    }

    @Test
    public void findUserByUsername() {
    }

    @Test
    public void findByUsernameLike() {
        System.out.println("Testing findRestaurantByNameLike");
        Mockito.when(userRepository.findByUsernameContainingIgnoringCase("eat"))
                .thenReturn(userList);
        assertEquals(3, userServices.findByUsernameLike("eat").size());
    }

    @Test
    public void delete() {
    }

    @Test
    public void update() {
    }

    @Test
    public void deleteAll() {
    }
}