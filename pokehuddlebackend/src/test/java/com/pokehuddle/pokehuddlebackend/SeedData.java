package com.pokehuddle.pokehuddlebackend;


import com.pokehuddle.pokehuddlebackend.models.Article;
import com.pokehuddle.pokehuddlebackend.models.Role;
import com.pokehuddle.pokehuddlebackend.models.User;
import com.pokehuddle.pokehuddlebackend.models.UserRoles;
import com.pokehuddle.pokehuddlebackend.services.ArticleServices;
import com.pokehuddle.pokehuddlebackend.services.RoleServices;
import com.pokehuddle.pokehuddlebackend.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * SeedData puts both known and random data into the database. It implements CommandLineRunner.
 * <p>
 * CoomandLineRunner: Spring Boot automatically runs the run method once and only once
 * after the application context has been loaded.
 */
@Transactional
@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    RoleServices roleServices;

    @Autowired
    UserServices userServices;

    @Autowired
    ArticleServices articleServices;

    /**
     * Generates test, seed data for our application
     * First a set of known data is seeded into our database.
     * Second a random set of data using Java Faker is seeded into our database.
     * Note this process does not remove data from the database. So if data exists in the database
     * prior to running this process, that data remains in the database.
     *
     * @param args The parameter is required by the parent interface but is not used in this process.
     */
    @Transactional
    @Override
    public void run(String... args) throws Exception {

        userServices.deleteAll();
        roleServices.deleteAll();
        articleServices.deleteAll();

        Role r1 = new Role("admin");
        Role r2 = new Role("user");

        r1 = roleServices.save(r1);
        r2 = roleServices.save(r2);

        User u1 = new User(
                "admin",
                "admin@pikapika.com",
                "password");
        u1.getRoles()
                .add(new UserRoles(u1,r1));
        u1.getRoles()
                .add(new UserRoles(u1,r2));
        userServices.save(u1);

        User u2 = new User(
                "neo",
                "neo@pikipika.com",
                "pika123");
        u2.getRoles()
                        .add(new UserRoles(u2, r2));
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
        userServices.save(u2);
        User u3 = new User(
                "scarlett",
                "scar@pikapika.com",
                "pika123"
        );
        u3.getRoles()
                        .add(new UserRoles(u3, r2));
        u3.getArticles()
                        .add(new Article("Mew vs Mewtwo",
                                "Sic de isto et tutius perducit ad actum ipsum, ut si dico “Ego autem vadam",
                                "Scarlett",
                                u2));
        userServices.save(u3);

    }

}
