package com.pokehuddle.pokehuddlebackend.services;

import com.pokehuddle.pokehuddlebackend.exceptions.ResourceNotFoundException;
import com.pokehuddle.pokehuddlebackend.models.Article;
import com.pokehuddle.pokehuddlebackend.models.Role;
import com.pokehuddle.pokehuddlebackend.models.User;
import com.pokehuddle.pokehuddlebackend.models.UserRoles;
import com.pokehuddle.pokehuddlebackend.repositories.RoleRepository;
import com.pokehuddle.pokehuddlebackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

//if you have a method in the class that is transactional, you should also make the entire class transactional
@Transactional
@Service(value = "userService")
public class UserServicesImpl implements UserServices{

    @Autowired
    private UserRepository userrepository;

    @Autowired
    private RoleRepository rolerepository;

    //whenever a method changes something in the database make it transactional
    @Transactional
    @Override
    public User save(User user) {
        User newUser = new User();

        //if there is an id, check to make sure its a valid id
        if (user.getUserid() != 0) {
            userrepository.findById(user.getUserid())
                    .orElseThrow(() -> new ResourceNotFoundException("User " + user.getUserid() + " not found!"));
            newUser.setUserid((user.getUserid()));
        }

        //primitive data type/String
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setNoEncryptPassword(user.getPassword());

        //collections
        //many to many
        newUser.getRoles().clear();
        for(UserRoles r: user.getRoles()) {
            Role newRole = rolerepository.findById(r.getRole().getRoleid())
                    .orElseThrow(() -> new ResourceNotFoundException("Role " + r.getRole().getRoleid() + " not found"));

            newUser.getRoles().add(new UserRoles(newUser, newRole));
        }

        //one to many
        newUser.getArticles().clear();
        for (Article a: user.getArticles()) {
            Article newArticle = new Article();
            newArticle.setAuthor(a.getAuthor());
            newArticle.setTitle(a.getTitle());
            newArticle.setBody(a.getBody());
            newArticle.setUser(newUser);

            newUser.getArticles().add(newArticle);
        }

        return userrepository.save(newUser);
    }

    @Override
    public List<User> findAllUsers() {
        List<User> list = new ArrayList<>();
        userrepository.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public User findUserById(long id) {

        return userrepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User " + id + " Not Found!"));
    }

    @Override
    public User findUserByUsername(String username) {
        User returnUser = userrepository.findByUsername(username);
        if(returnUser == null) {
            throw new ResourceNotFoundException("User "+ username + " Not Found");
        }
        return returnUser;
    }

    @Override
    public List<User> findByUsernameLike(String subusername) {
        List<User> returnList = userrepository.findByUsernameContainingIgnoringCase(subusername);
        return returnList;
    }

    @Transactional
    @Override
    public void delete(long userid) {
        if (userrepository.findById(userid).isPresent()) {
            userrepository.deleteById(userid);
        } else {
            throw new ResourceNotFoundException("User " + userid + " not found!");
        }
    }

    @Override
    public User update(User updateUser, long userid) {
        User currentUser = userrepository.findById(userid)
                .orElseThrow(() -> new ResourceNotFoundException("User " + userid + " not found!"));


        //if no new data is sent for these fields, then leave whatever is already on there.
        //data type String
        if (updateUser.getUsername() != null) {
            currentUser.setUsername(updateUser.getUsername());
        }

        if (updateUser.getEmail() != null) {
            currentUser.setEmail(updateUser.getEmail());
        }

        if (updateUser.getPassword() != null) {
            currentUser.setNoEncryptPassword(updateUser.getPassword());
        }

        //data types that are primitive such as long, boolean, char. etc are handles a little different, and have to be modified on the model level as well.

        //collections
        //collections are better handles as a complete replace to avoid confusion and complicated logic

        //many to many

        if(updateUser.getRoles().size() >0 ) {
            currentUser.getRoles().clear();
            for (UserRoles r : updateUser.getRoles()) {
                Role newRole = rolerepository.findById(r.getRole().getRoleid())
                        .orElseThrow(() -> new ResourceNotFoundException("Role " + r.getRole().getRoleid() + " not found"));

                currentUser.getRoles().add(new UserRoles(currentUser, newRole));
            }
        }

        //one to many
        if(updateUser.getArticles().size() > 0) {
            currentUser.getArticles().clear();
            for (Article a : updateUser.getArticles()) {
                Article newArticle = new Article();
                newArticle.setAuthor(a.getAuthor());
                newArticle.setTitle(a.getTitle());
                newArticle.setBody(a.getBody());
                newArticle.setUser(currentUser);

                currentUser.getArticles().add(newArticle);
            }
        }

        return userrepository.save(currentUser);
    }

//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    @Override
//    public void deleteAll() {
//        userrepository.deleteAll();
//    }
}
